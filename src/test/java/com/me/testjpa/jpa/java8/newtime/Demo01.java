package com.me.testjpa.jpa.java8.newtime;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Description:
 * @Author: ZhangGQ
 * @Date: 2019/9/6 11:41
 */
public class Demo01 {
    //时间新特性，时间戳转 yyyy-MM-dd HH:mm:ss
    @Test
    public void test6() {
        Long ss = 1567739422633l;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dtf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(ss), ZoneId.systemDefault()));
        System.out.println(format);

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        String format1 = now.format(dtf);
        System.out.println(format1);

        String str = "2018-12-10 12:22:22";
        LocalDateTime parse = LocalDateTime.parse(str, dtf);
        System.out.println(parse);
    }

    //字符串类型时间 转 时间戳
    @Test
    public void test7() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = "2019-09-06 11:10:22"; // 1567739422633l
        LocalDateTime parse = LocalDateTime.parse(str, dtf);
        long l = LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(l);
    }

    /*public static String convertTimeToString(Long time){
        Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time),ZoneId.systemDefault()));
    }*/
}
