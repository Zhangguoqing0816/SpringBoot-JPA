package com.me.testjpa.jpa.testdemo;

import com.me.testjpa.jpa.entity.User;
import com.me.testjpa.jpa.util.FileUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/6 10:18
 */
public class Demo3 {

    @Test
    public void test1() {
        String str = "C:\\Users\\zn\\Desktop\\通话JSON;C:\\Users\\zn\\Desktop\\通话JSON";
        String[] filePaths = str.split(";");

        List<String> urls = new ArrayList<>();
        for (int i = 0; i < filePaths.length; i++) {
            File testFile = new File(filePaths[i]);
                FileUtil.getFile(filePaths[i], urls);
        }
        System.out.println(urls.toString());
    }

    @Test
    public void test2() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        util1(list1, list2);
        System.out.println(list2.toString());
    }

    public void util1(List<String> list1, List<String> list2){
        for (int i = 0; i < list1.size() ; i++) {
            list2.add(list1.get(i).toLowerCase());
        }
    }

    @Test
    public void test3(){
        Integer a = 1,b = 2;
        b(a, b);
        System.out.println(a + "----" + b);
    }

    public void b(Integer a, Integer b){
        a = a+ b;
        System.out.println(a + "----" + b);
    }

    @Test
    public void test4(){
        User u1 = new User("1","2","3");
        User u2 = new User("3","2","1");
        b1(u1, u2);
        System.out.println(u2.toString());
    }

    public void b1(User u1,User u2){
        BeanUtils.copyProperties(u1, u2);
    }
}
