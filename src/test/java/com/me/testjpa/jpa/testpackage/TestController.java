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

    public static void test02(){
        List list = new ArrayList();
        list.add("132");
        List list2 = new ArrayList();
        list2.add("789");
        list2.add("897");
        list2.add("987");
        list.add(list2);
        System.out.println(list);

        System.out.println("<------------------------------------------>");

        List list3 = new ArrayList();
        list3.add("132");
        List list4 = new ArrayList();
        list4.add("789");
        list4.add("897");
        list4.add("987");
        list3.addAll(list4);
        System.out.println(list3);
    }

    public static void main(String[] args){
       /* List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.forEach(obj-> System.out.println(obj));*/

//        test01();

        test02();
    }

}
