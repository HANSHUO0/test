package com.hs;

import com.hs.model.Owner;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Auther: Administrator
 * @Data: 2021/11/18 0018 19:37
 */
public class ReadExcel {
    XSSFWorkbook wb = null;

    public void  init (){

        try {
            //excel模板路径
            File cfgFile = ResourceUtils.getFile("C:\\Users\\Administrator\\Desktop\\pb.xlsx");
            InputStream in = new FileInputStream(cfgFile);
            //读取excel模板
            wb = new XSSFWorkbook(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取 Excel 中的数据
     * @return
     */
    public List<Owner> read() {
        List<Owner> list = new ArrayList<Owner>();
        init ();
        //获取sheet表格，及读取单元格内容
        XSSFSheet sheet = null;
        try{
            sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            for (int i = 1; i<= lastRowNum; i++) {
                Owner Owner = new Owner();
                // Excel 中一共有3 列，暂时写死了
                for (int j = 0; j<3; j++) {
                    String cellValue ="";
                    if(null != sheet.getRow(i).getCell(j)){
                        sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
                        cellValue = sheet.getRow(i).getCell(j).getStringCellValue();
                        linkObj(Owner, j, cellValue);
                    }
                }
                System.out.println("================ 第"+i+"行完成==============");
                System.out.println(Owner.toString());
                list.add(Owner);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据 excel 文件的顺序给对象赋值
     * @param obj
     * @param j
     * @param val
     */
    private void linkObj(Owner obj, int j, String val) {
        switch (j) {
            case 0:
                obj.setName(val);
                break;
            case 1:
                obj.setCardId(val);
                break;
            case 2:
                obj.setArea(val);
                break;
            default:
                break;
        }

    }

    /**
     * 百分数的特殊处理
     * @param val
     * @return
     */
    private String tranNum(String val){
        Double b = Double.valueOf(val)* 100;
        return b.intValue() + "%";
    }

    public static void main(String[] args) {
        new ReadExcel().read();

    }
}
