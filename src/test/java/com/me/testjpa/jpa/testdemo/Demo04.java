package com.me.testjpa.jpa.testdemo;


import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/11 13:54
 */
public class Demo04 {

    public static void main(String[] args) {
        List<TestObj1> list = new ArrayList<>();
        List<String> list1 = Arrays.asList("A", "B", "C", "D");
        TestObj1 o1 = new TestObj1("1");
        TestObj1 o2 = new TestObj1("2");
        TestObj1 o3 = new TestObj1("3");
        TestObj1 o4 = new TestObj1("4");
        TestObj1 o5 = new TestObj1("5");
        TestObj1 o6 = new TestObj1("6");
        TestObj1 o7 = new TestObj1("7");
        TestObj1 o8 = new TestObj1("8");
        TestObj1 o9 = new TestObj1("9");
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);
        list.add(o6);
        list.add(o7);
        list.add(o8);
        list.add(o9);


        Iterator<String> iterator = list1.iterator();
        for (int i = 0; i < list.size(); i++) {
            TestObj1 o = list.get(i);
            if (iterator.hasNext()) {
                o.setName(iterator.next());
            } else {
                iterator = list1.iterator();
                o.setName(iterator.next());
            }
        }
        list.forEach(a -> System.out.println(a.toString()));
    }

    @Test
    public void test1() {
        List<TestObj1> list = new ArrayList<>();
        TestObj1 o1 = new TestObj1("1", new TestOjb2("1"));
        TestObj1 o2 = new TestObj1("2", new TestOjb2("2"));
        TestObj1 o3 = new TestObj1("3", new TestOjb2("3"));
        TestObj1 o4 = new TestObj1("4", new TestOjb2("3"));
        TestObj1 o5 = new TestObj1("5", new TestOjb2("4"));
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);
        Set<TestOjb2> collect = list.stream().map(testObj1 -> testObj1.getObj2())
                .collect(Collectors.toSet());
        for (TestOjb2 testOjb2 : collect) {
            System.out.println(testOjb2.toString());
        }
    }

    @Test
    public void test2() {
        TestObj1 o1 = new TestObj1("1");
        TestObj1 o2 = new TestObj1("2");
        TestObj1 o3 = new TestObj1("3");
        TestObj1 o4 = new TestObj1("4");
        TestObj1 o5 = new TestObj1("5");
        Set<TestObj1> set1 = new HashSet();
        set1.add(o1);
        set1.add(o2);
        set1.add(o3);
        set1.add(o4);
        set1.add(o5);
        Set<String> set2 = new HashSet();
        set2.add("o1");
        set2.add("o2");
        Iterator<TestObj1> iterator0 = set1.iterator();
        while (iterator0.hasNext()) {
            System.out.println(iterator0.next().toString());
        }
        System.out.println("-----------------------------");
        Iterator<TestObj1> iterator = set1.iterator();
        Iterator<String> iterator1 = set2.iterator();
        while (iterator.hasNext()) {
            TestObj1 next = iterator.next();
            if (iterator1.hasNext()) {
                next.setName(iterator1.next());
            } else {
                iterator1 = set2.iterator();
                next.setName(iterator1.next());
            }
        }
        Iterator<TestObj1> iterator2 = set1.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next().toString());
        }
    }

}
