package com.me.testjpa.jpa.xintexing.yufa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置四大核心函数式接口
 *
 * Consumer<T> : 消费性接口  返回值：T  调用方法：get();
 * Supplier<T> : 供给型接口  T get();
 * Function<T, R> : 函数型接口 R apply(T t)
 * Predicate<T> :断言型接口  boolean test<T t>
 */
public class TestLambda4 {

    //Consumer<T> 消费性接口

    public static void happy(double money, Consumer<Double> con){
        con.accept(money);
    }
    //调用
    public static void test01(){
        happy(10000, m -> System.out.println("玩耍消费了" + m + "元"));
    }

    // Suppplier<T> 供给型接口
    //需求：产生指定个数的整数，放到集合中
    public static List<Integer> getNumberList(int num, Supplier<Integer> su){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num ; i++) {
            Integer n = su.get();
            list.add(n);
        }
        return list;
    }
    //调用
    public static void test02(){
        List<Integer> numberList = getNumberList(10, () -> (int) (Math.random() * 100));
        System.out.println(numberList);
        numberList.forEach(num -> System.out.println(num));
    }

    // Function<T, R> 函数型接口
    public static String strHandler(String str, Function<String, String> fun){
        return fun.apply(str);
    }
    //调用
    public static void test03(){
        String s = strHandler("\t\t\t\t张国庆哥    ", (str) -> str.trim());
        System.out.println(s);
    }

    // Predicate<T> 断言型接口 将满足条件的字符串放入集合中
    public static List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> strList = new ArrayList<>();
        for (String str: list) {
            if(pre.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }
    //调用
    public static void test04(){
        List<String> list = Arrays.asList("Hello", "Lambda", "atguigu", "www", "ok");
        List<String> strings = filterStr(list, (str) -> str.length() > 3);
        System.out.println(strings);

    }

    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
        test04();
    }

}
