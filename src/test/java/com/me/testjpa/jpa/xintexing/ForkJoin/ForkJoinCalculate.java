package com.me.testjpa.jpa.xintexing.ForkJoin;/**
 * Created by Administrator on 2019/8/25.
 */

import java.util.concurrent.RecursiveTask;

/**
 * @author ZhangGuoQing---
 * @Date 2019/8/25 12:50
 * 并行流 和 串行流
 *  Fork / Join
 */

public class ForkJoinCalculate extends RecursiveTask<Long>{

    public ForkJoinCalculate(long start, long end){

    }
    private  long start;
    private long end;
    private  static final long THRESHOLD = 10000;


    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THRESHOLD){
            long sum = 0;
            for (long i = start; i<=  end; i++) {
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();//拆分子线程，同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
