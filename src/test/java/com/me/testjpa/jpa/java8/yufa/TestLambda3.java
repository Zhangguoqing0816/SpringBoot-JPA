package com.me.testjpa.jpa.java8.yufa;

import com.me.testjpa.jpa.entity.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  JAVA8 新特性 Lambda 练习
 */
public class TestLambda3 {

   static List<Employee> emps = Arrays.asList(
            new Employee("张三", "18", "9999"),
            new Employee("李四", "38", "6666"),
            new Employee("王五", "6", "3333"),
            new Employee("赵六", "55", "7777")
    );

    /**
     * List 對象排序
     */
    public static void test01(){
        Collections.sort(emps, (e1, e2) -> {
            if(Integer.valueOf(e1.getSal()) == Integer.valueOf(e2.getSal())){
                return e1.getEmpName().compareTo(e2.getEmpName());
            }else{
                return -Integer.compare(Integer.valueOf(e1.getSal()), Integer.valueOf(e2.getSal()));
            }
        });
        emps.forEach(emp -> System.out.println(emp));
    }

    //需求，用于处理字符串
    public static String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }

    public static void test02(){
        System.out.println("\t\t\t 万岁万岁万万岁");
        String s = strHandler( "\t\t\t 万岁万岁万万岁", str -> str.trim());
        System.out.println(s);
        String abcdefg = strHandler("ABCDEFG", str -> str.toLowerCase());
        System.out.println(abcdefg);
        String s1 = strHandler("万岁万岁万万岁", str -> str.substring(2, 7));
        System.out.println(s1);
    }

    //需求，对于两个 Long 型数据进行处理
    public static void op(Long l1, Long l2, MyFunction2<Long, Long> mf){
        System.out.println(mf.getValue(l1, l2));
    }

    public static void test03(){
        op(100L, 200L, (x, y)->x + y);
        op(100L, 200L, (x, y)->x * y);
    }

    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }


}
