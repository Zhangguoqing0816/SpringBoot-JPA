package com.me.testjpa.jpa.xintexing.stream;

import com.me.testjpa.jpa.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *  Stream 的三个操作步骤：
 *      1.创建 Stream
 *      2.中间操作
 *      3.终止操作
 */
public class Stream01 {
    //创建 Stream
    public static void test01(){
        // 1.可以通过 Collection 系列集合提供的 stream() 或者 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        // 2.通过 Arrays 中的静态方法 stream()
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);
        // 3.通过 Stream 类中的静态方法 of()
        Stream<String> aa = Stream.of("aa", "bb", "cc");
        // 4.创建无限流
            //迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 3);
        iterate.limit(10).forEach(System.out::println);
            //生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        test01();
    }
}
