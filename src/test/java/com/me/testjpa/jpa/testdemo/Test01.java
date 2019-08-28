package com.me.testjpa.jpa.testdemo;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Test01 {

    @Test
    public void main1() {
        String[] strArr = new String[]{"a","b","c"};
        List<String> strings = Arrays.asList(strArr);
        strings.stream().forEach(s -> {
            System.out.println(s);
        });
    }
}
