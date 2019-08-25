package com.me.testjpa.jpa.xintexing.ForkJoin;/**
 * Created by Administrator on 2019/8/25.
 */

import org.junit.Test;
import sun.rmi.runtime.Log;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author ZhangGuoQing---
 * @Date 2019/8/25 13:25
 */

public class TestForkJoin {

    @Test
    public void test01(){
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 5000000000l);
        long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println("耗费秒时间：" + Duration.between(start, end).getSeconds());
        System.out.println("耗费毫秒时间：" + Duration.between(start, end).toMillis());
    }

    /**
     * 普通for循环
     */
    @Test
    public void test02(){
        Instant start = Instant.now();
        Long sum = 0l;
        for (long i = 0; i < 5000000000l; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费秒时间：" + Duration.between(start, end).getSeconds());
        System.out.println("耗费毫秒时间：" + Duration.between(start, end).toMillis());
    }
}
