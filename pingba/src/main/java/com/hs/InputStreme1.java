package com.hs;

import java.io.*;

/**
 * Description:
 *
 * @Auther: Administrator
 * @Data: 2021/11/29 0029 11:23
 */
public class InputStreme1 {
    public static void main(String[] args) {

//        "D:\\studyTool\\tool\\apache-maven-3.6.3\\1.txt"
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
            String s = null;
            while ((s=br.readLine()) != null) {
                System.out.println(s);
//为 q 退出循环
                if ("q".equals(s)) {
                    break;
                } }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            }catch(IOException e) {}
        }
    }

    }
