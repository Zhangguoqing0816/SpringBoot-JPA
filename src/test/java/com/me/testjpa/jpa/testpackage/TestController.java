package com.me.testjpa.jpa.testpackage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.testjpa.jpa.entity.Employee;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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

    public static void test01(){
        List<Integer> integers = Arrays.asList(9, 5, 6, 87, 1);
        Collections.sort(integers);
        System.out.println(integers);
        Collections.reverse(integers);
        System.out.println(integers);

    }

    public static void main(String[] args){
       /* List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.forEach(obj-> System.out.println(obj));*/
        test01();
    }

}
