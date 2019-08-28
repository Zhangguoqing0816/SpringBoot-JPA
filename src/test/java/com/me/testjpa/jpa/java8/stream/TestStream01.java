package com.me.testjpa.jpa.java8.stream;

import com.me.testjpa.jpa.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API 练习
 */
public class TestStream01 {
    /**
     * 给定一个数字列表，返回一个由每个数的平方构成的列表 [1,2,3,4,5] -> [1,4,9,16,25]
     */
    public static void test01(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer[] nn = new Integer[]{1, 2, 3, 4, 5};

        List<Integer> collect = integers.stream().map(i -> i * i).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> collect1 = Stream.of(nn).map(i -> i * i).collect(Collectors.toList());
        System.out.println(collect1);

        Arrays.stream(nn).map(i -> i * i)
                .forEach(System.out::println);
    }
    static List<Employee> employeesList = Arrays.asList(
            new Employee("张三", "18", "9999", Employee.Status.FREE, 1040.0),
            new Employee("李四", "38", "6666", Employee.Status.BUSY, 2222.0),
            new Employee("王五", "6", "3333", Employee.Status.VOCATION, 7777.0),
            new Employee("赵六", "55", "7777", Employee.Status.FREE, 1543.0),
            new Employee("赵六", "56", "7777", Employee.Status.FREE, 5555.0)
    );
    /**
     * 怎样用 map 和 reduce 方法数一数流中有多少个 Employee
     */
    public static void test02(){
        Optional<Integer> reduce = employeesList.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());

        long count = employeesList.stream().count();
        System.out.println(count);
    }


     /* *
     * @param args
     */
    public static void main(String[] args) {
//        test01();
        test02();
    }
}
