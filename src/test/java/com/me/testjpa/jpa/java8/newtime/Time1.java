package com.me.testjpa.jpa.java8.newtime;

import org.junit.Test;

/**
 * 时间新特性
 */
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Time1 {

    @Test
    public void test1() throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call(){
                return LocalDate.parse("20161218", dtf);
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<LocalDate> submit = pool.submit(task);
            list.add(submit);
        }
        for (Future<LocalDate> future: list) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }

    @Test
    public void test2(){
    }
}
