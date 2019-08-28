package com.me.testjpa.jpa.java8.optional;

import com.me.testjpa.jpa.entity.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional 容器类的常用方法
 * Optional.of(T t) :创建一个 Optional 实例
 * Optional.empty() : 创建一个空的 Optional 实例
 * Optional.ofNullable(T t): 若 t 不为 null，创建 Optional 实例，否则创建空实例
 * isPresent(); 判断是否包含值
 * orElse(T t) : 如果调用对象包含值，返回该值，否则返回 t
 * orElseGet(Supplier s) : 如果调用对象半汉纸，返回该值，否则返回 s 获取的值
 * map(Function f) : 如果有值对其处理，并返回处理后的 Optional,否则返回 Optional.empty();
 * flatMap(Function mapper) : 与 map 类似，要求返回值必须是 Optional
 */
public class TestOptional01 {

    @Test
    public void test1(){
        Optional<Employee> o1 = Optional.of(new Employee());
        System.out.println(o1.get());
        Optional<Employee> o2 = Optional.of(null);
        System.out.println(o2.isPresent());
    }

    @Test
    public void test2(){
        //Optional<Employee> o1 = Optional.empty();
        System.out.println("1234");
        //System.out.println(o1.get());
    }

    @Test
    public void test3(){ // isPresent()
        Optional<Employee> employee = Optional.ofNullable(new Employee());
        System.out.println(employee);

        Optional<Employee> employee2 = Optional.ofNullable(new Employee());
        if(employee2.isPresent()){
            System.out.println(employee);
        }
    }

    @Test
    public void test4(){ // orElse orElseGet
        Optional<Employee> employee1 = Optional.ofNullable(new Employee());
        Employee employee2 = employee1.orElse(new Employee("08", "09", "10", Employee.Status.FREE));
        System.out.println(employee2);

        Optional<Employee> employee3 = Optional.ofNullable(null);
        Employee employee4 = employee3.orElse(new Employee("08", "09", "10", Employee.Status.FREE));
        System.out.println(employee4);

        Optional<Employee> employee5 = Optional.ofNullable(null);
        Employee employee = employee5.orElseGet(() -> new Employee());
        System.out.println(employee);
    }

    @Test
    public void test5(){  // map(Function f)   flatMap(Function mapper)
        Optional<Employee> employee = Optional.ofNullable(new Employee("08", "09", "10", Employee.Status.FREE));
        Optional<String> s = employee.map(e -> e.getEmpName());
        System.out.println(s.get());

        Optional<Employee> employee1 = Optional.ofNullable(new Employee("08", "09", "10", Employee.Status.FREE));
        Optional<String> s1 = employee1.flatMap(e -> Optional.of(e.getEmpName()));
        System.out.println(s1.get());
    }


    /**
     * .例题
     */
    //获取一个男人心中女神的名字，如果这个男人没有女神，那么久默认他的女生是苍老师
    public String getGodnessName(Man man){
       // return man.getGodness().getName();
        if(man != null){
            Godness godness = man.getGodness();
            if(godness != null){
                return godness.getName();
            }
        }
        return "苍老师";
    }

    public String getGodnessName2(Optional<NewMan> man){
         return man.orElse(new NewMan())
                 .getGodness()
                 .orElse(new Godness("苍老师"))
                 .getName();
    }

    @Test
    public void test6(){
        /*Man man = new Man();
        String godnessName = getGodnessName(man);
        System.out.println("这个小伙的女神是： " + godnessName);*/
        Optional<NewMan> op = Optional.ofNullable(null);
        String godnessName2 = getGodnessName2(op);
        System.out.println(godnessName2);

        Optional<NewMan> op2 = Optional.ofNullable(new NewMan());
        String godnessName3 = getGodnessName2(op2);
        System.out.println(godnessName3);

        Optional<Godness> gn = Optional.ofNullable(new Godness("波多老师"));
        Optional<NewMan> op3 = Optional.ofNullable(new NewMan(gn));
        String godnessName4 = getGodnessName2(op3);
        System.out.println(godnessName4);
    }

}
