package com.me.testjpa.jpa.java8.yufa;

import com.me.testjpa.jpa.entity.Employee;

import java.util.*;

/**
 *  JAVA8 Lambda 表达式
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestLambda1 {

    public static void main(String[] args) {
//        test1();
//        System.out.println("<----------------------------------------->");
//        test2();
//        System.out.println("<----------------------------------------->");
//        test3();
//        System.out.println("<----------------------------------------->");
//        test4();
//        System.out.println("<----------------------------------------->");
//        test5();
        test6();
    }

    //原来的匿名内部类
    public static void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
        ts.add(90);
        ts.add(9);
        ts.add(3);
        ts.add(96);
        ts.add(5);
        ts.stream().forEach(tt->{
            System.out.println(tt);
        });
    }

    // Lambda 匿名内部类
    public static void test2(){
        Comparator<Integer> com = (x, y)-> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
        ts.add(90);
        ts.add(9);
        ts.add(3);
        ts.add(96);
        ts.add(5);
        ts.stream().forEach(tt->{
            System.out.println(tt);
        });
    }

    // 查找公司年龄大于35的人
    public static void test3(){
        //Employee employee =
        List<Employee> employeesList = Arrays.asList(
                new Employee("张三", "18", "9999"),
                new Employee("李四", "38", "6666"),
                new Employee("王五", "6", "3333"),
                new Employee("赵六", "55", "7777")
        );
        List<Employee> realList = new ArrayList<>();
        for (Employee emp : employeesList) {
            if(Integer.valueOf(emp.getEmpName()) >= 35){
                realList.add(emp);
            }
        }
        realList.stream().forEach(rl->{
            System.out.println(rl.toString());
        });
    }

    // 查找公司年龄大于35的人 工资大于6500
    public static void test4(){
        //Employee employee =
        List<Employee> employeesList = Arrays.asList(
                new Employee("张三", "18", "9999"),
                new Employee("李四", "38", "6666"),
                new Employee("王五", "6", "3333"),
                new Employee("赵六", "55", "7777")
        );
        List<Employee> realList = new ArrayList<>();
        //方法1
        for (Employee emp : employeesList) {
            if(Integer.valueOf(emp.getEmpName()) >= 35){
                realList.add(emp);
            }
        }
        realList.stream().forEach(rl->{
            System.out.println(rl.toString());
        });

        System.out.println("<<---------------------------------->>");
        //方法二 写接口 匿名内部类
        realList.clear();
        List<Employee> list = filterEmployee(employeesList, new FilterList<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return Integer.valueOf(employee.getSal())>6500 ;
            }
        });
        list.stream().forEach(rl->{
            System.out.println(rl.toString());
        });
    }

    /**
     * 上面的借调方法
     * @param list
     * @param fl
     * @return
     */
    public static List<Employee> filterEmployee(List<Employee> list, FilterList<Employee> fl){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if(fl.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    public static void test5(){
        List<Employee> employeesList = Arrays.asList(
                new Employee("张三", "18", "9999"),
                new Employee("李四", "38", "6666"),
                new Employee("王五", "6", "3333"),
                new Employee("赵六", "55", "7777")
        );
        List<Employee> employees = filterEmployee(employeesList, employee -> Integer.valueOf(employee.getSal()) > 6500);
        employees.forEach(System.out::println);
    }

    public static void test6(){
        List<Employee> employeesList = Arrays.asList(
                new Employee("张三", "18", "9999"),
                new Employee("李四", "38", "6666"),
                new Employee("王五", "6", "3333"),
                new Employee("赵六", "55", "7777")
        );
        employeesList
                .stream()
                .filter(employee -> Integer.valueOf(employee.getSal()) > 6500)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("<<------->>");
        //提取里面的姓名
        employeesList.stream()
                .map(Employee::getEmpNo)
                .forEach(System.out::println);
    }



}
