package com.me.testjpa.jpa.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther:
 * @Description:
 * @Date: 2019/10/31 9:36
 */
public class TestStream {

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("1", "4", "7", "10", "8", "9");
        ArrayList<String> strings1 = Lists.newArrayList("1", "4", "71", "101", "81", "91");
        ArrayList<String> strings2 = Lists.newArrayList("1", "4", "72", "102", "82", "92");
        List list = new ArrayList();
        list.add(strings);
        list.add(strings1);
        list.add(strings2);
        List<Stream<String>> collect = Stream.of(strings, strings1, strings2).map(Collection::stream).collect(Collectors.toList());
        System.out.println(collect);
    }
}
