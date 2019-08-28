package com.me.testjpa.jpa.java8.newtime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试 SimpleDateFormat 线程安全问题
 */
public class TestsSimpleDateFormat {

    public static void main(String[] args) throws Exception{
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
//                return sf.parse("20190612");
                return DateFormatThreadLocal.convert("20190612");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> submit = pool.submit(task);
            list.add(submit);
        }

        for (Future<Date> future: list) {
            System.out.println(future.get());
        }
        pool.shutdown();

    }

}
