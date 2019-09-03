package com.me.testjpa.jpa.testdemo;


import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test01 {

    @Test
    public void main1() {
        String[] strArr = new String[]{"a","b","c"};
        List<String> strings = Arrays.asList(strArr);
        strings.stream().forEach(s -> {
            System.out.println(s);
        });
    }

    @Test
    public void test() {
        List<String> list1 = Arrays.asList("1", "2", "3");
        List<String> list2 = Arrays.asList("4", "5", "6");
        List<Stream<String>> collect = Stream.of(list1, list2).map(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);
        /*交集*/
        List<String> unionAllList = Stream.of(list1, list2).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(unionAllList);
        /*并集*/
        List<String> unionList = Stream.of(list1, list2).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        System.out.println(unionList);
    }
}
