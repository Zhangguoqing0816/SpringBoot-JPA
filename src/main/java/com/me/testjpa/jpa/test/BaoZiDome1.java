package com.me.testjpa.jpa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用 synchronized同步锁
 * wait()挂起和 notifyAll()唤醒机制
 * 实现生产最多20个包子,最低不能为0个包子的生产消费模式
 */
@SuppressWarnings("all")
public class BaoZiDome1 {


    public static void main(String[] args) {

        BaoZi baoZi = new BaoZi();

//        方式一: 使用线程对象
        ThreadGroup group1 = new ThreadGroup("生产包子组");
        ThreadGroup group2 = new ThreadGroup("消费包子组");
        new Thread(group1, new MakeBaozi(baoZi), "生产1:").start();
        new Thread(group1, new MakeBaozi(baoZi), "生产2:").start();
        new Thread(group2, new EatBaozi(baoZi), "消费3:").start();
        new Thread(group2, new EatBaozi(baoZi), "消费4:").start();

        //方式二:线程池
        ExecutorService pool = Executors.newFixedThreadPool(4);
        pool.submit(new MakeBaozi(baoZi));
        pool.submit(new MakeBaozi(baoZi));
        pool.submit(new EatBaozi(baoZi));
        pool.submit(new EatBaozi(baoZi));
    }

    static class BaoZi {
        private static List<String> list = new ArrayList<String>();

        public synchronized void makeBaozi() throws InterruptedException {
            //如果包子数量超过20,则该线程挂起让其他线程执行

            while (list.size() >= 20) {
                wait();
            }
            list.add("包子" + list.size());
            System.out.println(Thread.currentThread().getName() + "当前还有" + list.size() + "个包子");
            notifyAll();
        }


        public synchronized void eatBaozi() throws InterruptedException {
            //如果包子数量为0,则该线程挂起让其他线程生产包子
            while (list.size() <= 1) {
                wait();
            }

            list.remove(0);
            System.out.println(Thread.currentThread().getName() + "当前还有" + list.size() + "个包子");
            notifyAll();
        }

    }

    static class MakeBaozi implements Runnable {
        private BaoZi baoZi;

        public MakeBaozi(BaoZi baoZi) {
            this.baoZi = baoZi;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    baoZi.makeBaozi();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class EatBaozi implements Runnable {
        private BaoZi baoZi;

        public EatBaozi(BaoZi baoZi) {
            this.baoZi = baoZi;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    baoZi.eatBaozi();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
