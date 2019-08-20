package com.me.testjpa.jpa.util;

import com.csvreader.CsvReader;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class CSVUtils {

    public static final String CSV_TYPE_XLSX = "csv";


    /**
     * 解析csv文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static List<Map<String,String>> readCsv(File file)throws Exception{
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(stream,"GBK");
        CsvReader cr = new CsvReader(input);
        List<String> list = new ArrayList<>();
        List<Map<String,String>> mapList = new ArrayList<>();
        int index = 0;
        while (cr.readRecord()) {

            if(index == 0){
                for (int i = 0; i<cr.getValues().length; i++) {
                    list.add(cr.getValues()[i]);
                }
            }else{
                Map<String,String> map = new HashMap<>();
                for (int i = 0; i<cr.getValues().length; i++) {
                    map.put(list.get(i),cr.getValues()[i]);
                }
                mapList.add(map);
                for(Map.Entry<String, String> entry : map.entrySet()){
                    System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
                }
            }
            index++;

        }
        return mapList;
    }

    /**
     * 模板的创建
     *
     * @param exportData 数据List<Map<String, String>>
     * @param headMap 表头 LinkedHashMap
     * @param outPutPath 输出路径
     * @param fileName 文件名称
     * @return File，文件创建完之后下载需要用
     */
    public static File createCSVFile(List<Map<String, String>> exportData, LinkedHashMap headMap,
                                     String outPutPath, String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdir();
            }
            // 定义文件名格式并创建
            String path = outPutPath.concat(fileName).concat(".csv");
            csvFile = new File(path);
            // UTF-8使正确读取分隔符","
            //csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GBK"), 1024);
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);
            // 写入文件头部
            for (Iterator propertyIterator = headMap.entrySet().iterator(); propertyIterator
                    .hasNext(); ) {
                Map.Entry propertyEntry = (Map.Entry) propertyIterator
                        .next();
                csvFileOutputStream
                        .write("" + (String) propertyEntry.getValue() != null ? (String) propertyEntry
                                .getValue() : "" + "");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
                Object row = (Object) iterator.next();
                Map<String, String> rowMap = (Map<String, String>) row;
                for (Iterator propertyIterator = headMap.entrySet().iterator(); propertyIterator
                        .hasNext(); ) {
                    Map.Entry propertyEntry = (Map.Entry) propertyIterator
                            .next();
//                    csvFileOutputStream.write((String) BeanUtils.getProperty(
//                            row, (String) propertyEntry.getKey()));
                    if (rowMap.containsKey((String) propertyEntry.getKey())) {
                        String value = rowMap.get((String) propertyEntry.getKey());
                        if (StringUtils.isNotEmpty(value)) {
                            csvFileOutputStream.write(value);
                        }
                    }
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }


    /**
     * 删除单个文件
     *
     * @param filePath 文件目录路
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath.concat(".csv"));
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {
        List exportData = new ArrayList<Map>();
        Map row1 = new LinkedHashMap<String, String>();
        row1.put("1", "name");
        row1.put("2", "sex");
        row1.put("3", "age");
        row1.put("4", "address");
        exportData.add(row1);
        row1 = new LinkedHashMap<String, String>();
        row1.put("1", "21");
        row1.put("2", "22");
        row1.put("3", "23");
        row1.put("4", "24");
        exportData.add(row1);
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "姓名");
        map.put("2", "性别");
        map.put("3", "年龄");
        map.put("4", "地址");

        String path = "d:/export/";
        String fileName = "文件导出";
        File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
    }
}
