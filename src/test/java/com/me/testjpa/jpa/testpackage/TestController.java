package com.me.testjpa.jpa.testpackage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.testjpa.jpa.entity.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestController {

    public static void main2(String[] args) {
        System.out.println(new Date());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(new Date()));

        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = new Employee("123", "456", "789", "000", new Date());
        try {
            String str = objectMapper.writeValueAsString(employee);
            System.out.println(str);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
