package com.me.testjpa.jpa.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {

    /**
     * 读取文件内容
     * @param path
     * @return
     */
    public static String readFile(String path){
        String result = new String();
        File file = new File(path);
        result = readFile(file);
        return result;
    }


    public static String readFile(File file){
        String result = new String();
        if (file.exists()) {
            LineIterator it = null;
            try {
                it = FileUtils.lineIterator(file, "utf-8");
                while (it.hasNext()) {
                    String line = it.nextLine();
                    result += line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                LineIterator.closeQuietly(it);
            }
        }
        return result;
    }


    /**
     * 读取文件内容
     * @param fileName
     * @return
     */
    public static String readFileByLines(String fileName) {
        String result = new String();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                result += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }

    /**
     * 文件的下载
     * @param response
     * @param file
     */
    public static void download(HttpServletResponse response,
                                File file){
        //response.setCharacterEncoding("GBK");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());
        ServletOutputStream out;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1) {
                b = inputStream.read(buffer);
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
