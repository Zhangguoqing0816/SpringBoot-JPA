package com.me.testjpa.jpa.java8.stream;

import com.me.testjpa.jpa.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream API 终止操作
 *  查找与匹配
 *      allMatch 检查是否匹配所有元素
 *      anyMatch 检查是否至少匹配一个元素
 *      noneMatch 检查是否没有匹配所有元素
 *      findFirst 返回第一个元素
 *      findAny   返回当前流中的任意元素
 *      count     返回流中元素的总和
 *      max       返回流中最大值
 *      min       返回流中最小值
 *
 * 规约
 * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)  可以将流中的元素反复结合，得到一个值
 *
 * 收集
 * collect 将流转换为其他形式， 接收一个 Collector 接口实现，用于给 Stream 中元素做汇总的方法
 */
public class Stream03 {
    static List<Employee> employeesList = Arrays.asList(
            new Employee("张三", "18", "9999", Employee.Status.FREE, 1040.0),
            new Employee("李四", "38", "6666", Employee.Status.BUSY, 2222.0),
            new Employee("王五", "6", "3333", Employee.Status.VOCATION, 7777.0),
            new Employee("赵六", "55", "7777", Employee.Status.FREE, 1543.0),
            new Employee("赵六", "56", "7777", Employee.Status.FREE, 5555.0)
    );

    //终止操作
    public static void test01(){
        //  allMatch 查看是不是全部都是 BUSY
        boolean b = employeesList.stream()
                .allMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        //  anyMatch    查看至少有一个是 BUSY
        boolean b2 = employeesList.stream()
                .anyMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        //noneMatch 查看里面是不是全部都不是 busy
        boolean b3 = employeesList.stream()
                .noneMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        //findFirst 按工资排序，在获取第一个
        Optional<Employee> first = employeesList.stream()
                .sorted((e1, e2) -> Integer.compare(Integer.valueOf(e1.getSal()), Integer.valueOf(e2.getSal())))
                .findFirst();
        System.out.println(first.get());
        //findAny   随便找一个处于空闲状态的人
        Optional<Employee> any = employeesList.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());
        //count
        long count = employeesList.stream().count();
        System.out.println(count);
        //max
        Optional<Employee> max = employeesList.stream()
                .max((e1, e2) -> Integer.compare(Integer.valueOf(e1.getSal()), Integer.valueOf(e2.getSal())));
        System.out.println(max.get());
        //min  最低工资的人
        Optional<Employee> min = employeesList.stream()
                .min((e1, e2) -> Integer.compare(Integer.valueOf(e1.getSal()), Integer.valueOf(e2.getSal())));
        System.out.println(min.get());
        //获取最低工资的人的工资数
        Optional<String> min1 = employeesList.stream()
                .map(Employee::getSal)
               // .min(String::compareTo);
                .min((e1, e2) -> Integer.compare(Integer.valueOf(e1), Integer.valueOf(e2)));
        System.out.println(min1.get());
    }
    
    //规约 reduce
    public static void test02(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = integers.stream()
                // x 是起始值，y 为数组中的每个值
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        //算下当前人员工资的总和
        Optional<String> reduce1 = employeesList.stream()
                .map(Employee::getSal)
                .reduce((e1, e2) -> String.valueOf(Integer.valueOf(e1) + Integer.valueOf(e2)));
        System.out.println(reduce1);
    }

    //收集 collect  提取所有人的名字，然后把名字添加到集合中
    public static void test03(){
        employeesList.stream()
                .map(Employee::getEmpNo)
                //.collect(Collectors.toList())  // list
               // .collect(Collectors.toSet())   // set
                .collect(Collectors.toCollection(HashSet::new))  // 想要其他类型就用这个
                .forEach(System.out::println);
    }
    // collect 操作
    public static void test04(){
        // 总数
        Long collect = employeesList.stream()
                .collect(Collectors.counting());
        //工资平均值
        Double collect1 = employeesList.stream()
                .collect(Collectors.averagingDouble(emp -> Double.valueOf(emp.getSal())));
        System.out.println(collect1);
        //工资总和
        Double collect2 = employeesList.stream()
                .collect(Collectors.summingDouble(emp -> Double.valueOf(emp.getSal())));
        System.out.println(collect2);
        // 工资的最大值
        Optional<Integer> collect3 = employeesList.stream()
                .map(emp -> Integer.valueOf(emp.getSal()))
                .collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(collect3.get());
        // 工资的最小值的人
        Optional<Employee> collect4 = employeesList.stream()
                .collect(Collectors.minBy((e1, e2) -> Integer.compare(Integer.valueOf(e1.getSal()), Integer.valueOf(e2.getSal()))));
        System.out.println(collect4.get());
    }
    // collect 分组
    public static void test05(){
        //分组
        Map<Employee.Status, List<Employee>> collect = employeesList.stream()
                .collect(Collectors.groupingBy((emp) -> emp.getStatus()));
        System.out.println(collect);
        //多级分组
        Map<Employee.Status, Map<String, List<Employee>>> collect1 = employeesList.stream()
                .collect(Collectors.groupingBy(emp -> emp.getStatus(), Collectors.groupingBy(emp -> {
                    if (Integer.valueOf(emp.getEmpName()) > 35) {
                        return "青年";
                    } else if (Integer.valueOf(emp.getEmpName()) < 35) {
                        return "小孩";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(collect1);
    }

    // collect 分区
    public static void test06() {
        Map<Boolean, List<Employee>> map = employeesList.stream()
                .collect(Collectors.partitioningBy(emp -> Integer.valueOf(emp.getSal()) > 7000));
        System.out.println(map);
    }

    public static void test07() {
        Double collect =
                employeesList.stream()
                .collect(Collectors.summingDouble(Employee::getX));
        System.out.println(collect);

        DoubleSummaryStatistics dss = employeesList.stream()
                .mapToDouble(Employee::getX).summaryStatistics();
        System.out.println(dss.getMax());//最大值
        System.out.println(dss.getAverage());//平均值
        System.out.println(dss.getCount());//总个数
        System.out.println(dss.getMin());//最小值
        System.out.println(dss.getSum());//总和
    }

    public static void test08() {
        String s = employeesList.stream()
                .map(Employee::getEmpNo)
                .collect(Collectors.joining(",", "===", "==="));
        System.out.println(s);
    }

    public static void main(String[] args){
//        test01();
//        test02();
//        test03();
//        test04();
//        test05();
//        test06();
//        test07();
        test08();
    }
}
