package com.me.testjpa.jpa.testdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.me.testjpa.jpa.model.JsonDataModel;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import springfox.documentation.spring.web.json.Json;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/5 11:22
 */
public class Demo02 {

    @Test
    public void test1() {
        List<JsonDataModel> list = new ArrayList<>();
        //list.add(new JsonDataModel());
        System.out.println(list.isEmpty());

        long time1 = 1527767665;
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time1 * 1000));
        System.out.println(result1);
        System.out.println(new Date().getTime());
    }

}
