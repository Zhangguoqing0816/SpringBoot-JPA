package com.me.testjpa.jpa.jsonPack;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.me.testjpa.jpa.model.JsonDataModel;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/5 17:30
 */
public class JsonUtils {

    @Test
    public void test() {
        String path = "C:\\Users\\zn\\Desktop\\通话JSON";
        File file = new File(path);
        if(file.exists()){
            boolean directory = file.isDirectory();
            System.out.println("是不是文件夹：\n" + directory);
            String parent = file.getParent();
            System.out.println("父路径：\n" + parent);
            if(directory){
                File[] files = file.listFiles();
                for (File file1: files) {
                    System.out.println("文件名称： \n" + file1.getName());
                    Map map = toMap(file1);
                }
            }
        }else{
            System.out.println("没有此路径");
        }
    }

    public Map toMap(File file){
        try {
            String jsonStr = IOUtils.toString(new InputStreamReader(new FileInputStream(file)));
            System.out.println("文件内容\n" + jsonStr);
            Map<String, Object> map = (Map<String, Object>) JSON.parse(jsonStr);
            System.out.println("map内容：\n" + map);
            boolean ok1 = map.containsKey("ok");
            String data = (String) map.get("data");
            toJsonObject(data);
            System.out.println("map里面的data: \n" + data);
            List<Map<String, String>> parse = (List<Map<String, String>>) JSON.parse(data);
            for (Map<String, String> map2 : parse) {
                for (Map.Entry<String, String> entry: map2.entrySet()) {
                    System.out.println(entry.getKey() + "-----" + entry.getValue());
                }
                System.out.println("-------");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public JsonObject toJsonObject(String jsonString){

        /*JSONArray objects = JSONArray.parseArray(jsonString);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);

        }*/

        List<JsonDataModel> jsonDataModels = JSON.parseArray(jsonString, JsonDataModel.class);
        for (JsonDataModel a : jsonDataModels) {
            System.out.println(a.toString());
        }

        return null;
    }


    public static Map<String, Object> jsonToMap(File file){
        try {
            String jsonString = IOUtils.toString(new InputStreamReader(new FileInputStream(file)));
            Map<String, Object> map = (Map<String, Object>)JSON.parse(jsonString);
            boolean hasOK = map.containsKey("ok");
            if(hasOK){
                if((int)map.get("ok") == 0){
                    String dataStr = (String) map.get("data");
                    List<JsonDataModel> converses = JSON.parseArray(dataStr, JsonDataModel.class);
                    map.put("data", converses);
                    return map;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test
    public void test2() {
        String path = "C:\\Users\\zn\\Desktop\\通话JSON\\多次跳票.mp3(1).json";
        File file = new File(path);
        //Map<String, Object> stringObjectMap = jsonToMap(file);
        //List<JsonDataModel> converses = (List<JsonDataModel>) stringObjectMap.get("data");

        List<JsonDataModel> converses = jsonToMap1(file);
        converses.stream().forEach(c -> System.out.println(c.toString()));
    }

    public static List<JsonDataModel> jsonToMap1(File file){
        try {
            String jsonString = IOUtils.toString(new InputStreamReader(new FileInputStream(file)));
            JSONObject jsonObject = JSON.parseObject(jsonString);
            boolean ok = jsonObject.containsKey("ok");
            if(ok){
                if((int)jsonObject.get("ok") == 0){
                    List<JsonDataModel> converses = new ArrayList<>();
                    String data = (String) jsonObject.get("data");
                    System.out.println(data);
                    converses = JSON.parseArray(data, JsonDataModel.class);
                    return converses;
                }else{
                    return null;
                }
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
