package com.hs;

import com.hs.model.Owner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.output.ByteArrayOutputStream;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Description:
 *
 * @Auther: Administrator
 * @Data: 2021/11/18 0018 19:47
 */
public class MyMain {
    public static void fillTemplate() {

        List<Owner> read = new ReadExcel().read();


        System.out.println(read);
        // 模板路径
        String templatePath = "C:\\Users\\Administrator\\Desktop\\test.pdf";
        if(null != read && !read.isEmpty()){
            for(int a = 0; a < read.size(); a++){
                Owner obj = read.get(a);
                // 生成的新文件路径
                String newPDFPath = "E:/test/" + "平坝" + obj.getName() + ".pdf";
                PdfReader reader;
                FileOutputStream out;
                ByteArrayOutputStream bos;
                PdfStamper stamper;
                try {
                    out = new FileOutputStream(newPDFPath);// 输出流
                    reader = new PdfReader(templatePath);// 读取pdf模板
                    bos = new ByteArrayOutputStream();
                    stamper = new PdfStamper(reader, bos);
                    AcroFields form = stamper.getAcroFields();
                    String[] str = { obj.getName(),obj.getCardId(),obj.getArea()};
                    int i = 0;
                    java.util.Iterator<String> it = form.getFields().keySet().iterator();
                    while (it.hasNext()) {
                        String name = it.next().toString();
                        System.out.println(name + "============" + str[Integer.parseInt(name)]);
                        form.setField(name, str[Integer.parseInt(name)]);
                    }
                    stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
                    stamper.close();
                    Document doc = new Document();
                    PdfCopy copy = new PdfCopy(doc, out);
                    doc.open();
                    PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
                    copy.addPage(importPage);
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        fillTemplate();
    }
}
