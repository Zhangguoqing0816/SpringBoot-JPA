package com.me.testjpa.jpa.java8.yufa;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *  Lambda 基础语法
 *  无参无返回值  test01
 *  一个参数无返回值 test02
 *  有两个以上的参数 并且有返回值，并且 Lambda 体中有多条语句 test03
 */
public class TestLambda2 {

    /**
     * 无参无返回值
     */
    public static void test01(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda");
            }
        };
        runnable.run();
        System.out.println("<--------------------->");
        Runnable runnable1 = () -> System.out.println("Hello Lambda");
        runnable1.run();
    }

    /**
     * 有一个参数无返回值
     */
    public static void test02(){
        // A
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("万岁万岁万万岁A");
        // B  一个参数时小括号可以不要
        Consumer<String> consumer2 = x -> System.out.println(x);
        consumer2.accept("万岁万岁万万岁B");
    }

    /**
     *  有两个以上的参数 并且有返回值，并且 Lambda 体中有多条语句
     */
    public static void test03(){
        //比较两个 Integer 的大小
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("132");
            return Integer.compare(x, y);
        };
    }

    /**
     *  有两个以上的参数 并且有返回值，并且 Lambda 体中有 1 条语句，
     *  大括号可以不写，返回值 可以不写 return
     */
    public static void test04(){
        //比较两个 Integer 的大小
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
    }

    public static void test05(){
        System.out.println(operation(10, x -> x * x));
        System.out.println(operation(20, y -> y + 20));
    }

    public static Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }



    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
//        test04();
        test05();
    }
}
