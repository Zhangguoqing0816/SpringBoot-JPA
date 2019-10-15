package com.me.testjpa.jpa.test;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 synchronized同步锁
 * wait()挂起和 notifyAll()唤醒机制
 * 实现生产最多20个包子,最低不能为0个包子的生产消费模式
 */
@SuppressWarnings("all")
public class BaoZiDome2 {

    public static void main(String[] args) {
        BaoZi baoZi = new BaoZi();
        ThreadGroup group1 = new ThreadGroup("生产包子组");
        ThreadGroup group2 = new ThreadGroup("消费包子组");
        new Thread(group1, new MakeBaozi(baoZi), "生产1:").start();
        new Thread(group1, new MakeBaozi(baoZi), "生产2:").start();
        new Thread(group2, new EatBaozi(baoZi), "消费3:").start();
        new Thread(group2, new EatBaozi(baoZi), "消费4:").start();
    }

    static class BaoZi {
        private static List<String> list = new ArrayList<String>();
        //创建同步锁对象
        private Lock lock = new ReentrantLock();
        //使用lock建立生产者的condition对象
        private Condition makeCondition = lock.newCondition();
        //使用lock建立消费者的condition对象
        private Condition eatCondition = lock.newCondition();

        public void makeBaozi() throws InterruptedException {
            Thread.currentThread().yield();
            lock.lock();
            //如果包子数量超过20,则该线程挂起让其他线程执行
            while (list.size() >= 20) {
                makeCondition.await();//让程序挂起
            }
            list.add("包子" + list.size());
            System.out.println(Thread.currentThread().getName() + "正在生产第" + list.size() + "个包子");

            //使用消费者的Condition唤起消费线程
            eatCondition.signalAll();
            //释放锁
            lock.unlock();

        }


        public void eatBaozi() throws InterruptedException {
            Thread.currentThread().yield();
            lock.lock();
            //如果包子数量为0,则该线程挂起让其他线程生产包子
            while (list.size() <= 0) {
                eatCondition.await();//让消费线程挂起
            }
            list.remove(0);
            System.out.println(Thread.currentThread().getName() + "正在吃第" + list.size() + "个包子");
            //使用生产的Condition唤醒生产线程
            makeCondition.signalAll();
            //释放锁
            lock.unlock();
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
                    //  Thread.sleep(100);
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
                    // Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
