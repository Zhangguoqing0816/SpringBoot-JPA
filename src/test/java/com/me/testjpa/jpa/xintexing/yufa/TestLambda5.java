package com.me.testjpa.jpa.xintexing.yufa;

import com.me.testjpa.jpa.entity.Employee;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 *  Lambda 方法引用 / 构造器引用 / 数组引用
 *  方法引用：若 Lambda 体中的内容有方法已经实现，我们可以使用 “方法引用”
 *      (可以理解为方法引用是 Lambda 表达式的另外一种表现形式)
 *  主要有三种语法格式：
 *          (注意： Lambda 体中调用方法的参数类表和返回值类型要和函数式接口中抽象方法的参数列表和返回值一致
 *                 若 Lambda 参数列表中的第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，可以使用 Class::method
 *          )
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名
 *  构造器引用：
 *      Class::new
 *          (注意：需要调用的构造器的参数列表要与函数式接口中的抽象方法的参数列表保持一致)
 *  数组引用:
 *
 */
public class TestLambda5 {
    //对象::实例方法名
    public static void test01(){
        PrintStream ps = System.out;
        Consumer<String> consumer = ps::println;
        consumer.accept("haha");

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("abcdef");
    }
    public static void test02(){
        Employee emp = new Employee();
        Supplier<String> su = () -> emp.getEmpName();
        String str = su.get();
        System.out.println(str);

        Employee employee = new Employee();
        Supplier<String> supplier = employee::getEmpName;
        String s = supplier.get();
        System.out.println(s);
    }

    //类::静态方法名
    public static void test03(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        int compare1 = com.compare(5, 3);
        System.out.println(compare1);

        Comparator<Integer> com1 = Integer::compare;
        int compare = com1.compare(1, 2);
        System.out.println(compare);
    }

    //类::实例方法名
    public static void test04(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        boolean test = bp.test("123", "321");
        boolean test1 = bp.test("123", "123");
        System.out.println(test + "----" + test1);

        BiPredicate<String, String> bp2 = String::equals;
        boolean test2 = bp2.test("zhang", "zhang");
        boolean test3 = bp2.test("zhang", "zhangguoqing");
        System.out.println(test2 + "----" + test3);
    }


    //构造器引用
    public static void test05(){
        Supplier<Employee> s1 = () -> new Employee();
        Employee employee = s1.get();
        
        Supplier<Employee> s2 = Employee::new;
        Employee employee1 = s2.get();

        Function<String, Employee> fun1 = (str) -> new Employee(str);
        Employee apply = fun1.apply("132");
        System.out.println(apply);

        Function<String, Employee> fun2 = Employee::new;
        Employee apply1 = fun2.apply("465");
        System.out.println(apply1);

        BiFunction<String, String, Employee> bf = Employee::new;
        Employee e = bf.apply("222", "111");
        System.out.println(e);
    }

    //数组引用
    public static void test06(){
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] apply2 = fun2.apply(20);
        System.out.println(apply2.length);
    }

    public static void main(String[] args) {
//        testo01();
//        test02();
//        test03();
//        test04();
//        test05();
        test06();
    }
}
