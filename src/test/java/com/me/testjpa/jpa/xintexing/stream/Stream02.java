package com.me.testjpa.jpa.xintexing.stream;

import com.me.testjpa.jpa.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 *  Stream 中间操作
 *  筛选与切片
 *  filter - 接受 Lambda 从流中排除某些元素
 *  limit  - 截断流，是某些元素不超过给定的数量
 *  skip(n)- 跳过元素，返回一个扔掉了前 n 个元素的流，若流中元素不足 n 个，则返回一个空流，与 limit(n) 互补
 *  distince- 筛选，通过流所生成的 hashCode() 和 equals() 去除重复元素
 *
 *  映射：
 *      map 接收 Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
 *      flatMap 接收一个函数作为参数，将流中的每一个值都换成另外一个流，然后把所有流连接成一个流
 */
public class Stream02 {
    static List<Employee> employeesList = Arrays.asList(
            new Employee("张三", "18", "9999"),
            new Employee("李四", "38", "6666"),
            new Employee("王五", "6", "3333"),
            new Employee("赵六", "55", "7777")
    );

    static List<Employee> employeesList2 = Arrays.asList(
            new Employee("张三", "18", "9999"),
            new Employee("李四", "38", "6666"),
            new Employee("王五", "6", "3333"),
            new Employee("赵六", "55", "7777"),
            new Employee("赵六", "55", "7777"),
            new Employee("赵六", "55", "7777")
    );

    // filter 内部迭代
    public static void test01(){
        //中间操作： 不会执行任何擦欧总
        employeesList.stream()
                .filter((e) -> {
                            System.out.println("Stream API过滤操作");
                            return Integer.valueOf(e.getSal()) > 6500;
                        }
                )
                //终止操作： 一次性执行全部内容，即“惰性求值”
                .forEach(System.out::println);
    }

    //外部迭代
    public static void test02(){
        Iterator<Employee> iterator = employeesList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    // limit 截断
    public static void test03(){
        employeesList.stream()
                .filter(e -> {
                    System.out.println("短路！！！");
                    return Integer.valueOf(e.getSal()) > 5000;
                }).limit(2)
                .forEach(System.out::println);
    }

    //skip(n) 跳过
    public static void test04(){
        employeesList.stream()
                .filter(employee -> Integer.valueOf(employee.getSal()) > 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    //distinct 去重
    public static void test05(){
        employeesList2.stream()
                .filter(e -> Integer.valueOf(e.getSal()) > 6000)
               // .skip(2)
                .distinct()
                .forEach(System.out::println);
    }


    //映射 map
    static List<String> strings = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
    public static void test06(){
        strings.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("<------->");

        employeesList.stream()
                .map(employee -> employee.getEmpNo())
                .forEach(System.out::println);
    }

    // flatMap
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch: str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
    public static void test07(){
        Stream<Stream<Character>> streamStream = strings.stream()
                .map(str -> filterCharacter(str));
        streamStream.forEach(sm ->{
            sm.forEach(System.out::println);
        });

        System.out.println("<--------->");

        Stream<Character> characterStream = strings.stream()
                .flatMap(str -> filterCharacter(str));
        characterStream.forEach(character -> System.out.println(character));
    }


    /**
     * 排序
     *  sorted() 自然排序（Comparable）
     *  sorted(Comparator com) 定制排序（Comparator）
     */
    public static void test08(){
        List<String> list = Arrays.asList("ccc","aaa","bbb","ddd","eee");
        list.stream().sorted().forEach(System.out::println);
        employeesList.stream()
                .sorted((e1, e2)->{
                    return Integer.valueOf(e1.getSal()).compareTo(Integer.valueOf(e2.getSal()));
                })
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
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
