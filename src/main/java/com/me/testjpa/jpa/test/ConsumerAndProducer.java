package com.me.testjpa.jpa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther:
 * @Description: 生产消费
 * @Date: 2019/10/15 11:25
 */
public class ConsumerAndProducer {


    public static void main(String[] args) {
        Food food = new Food();
        new Thread(new ProducerFoodClass(food)).start();
        new Thread(new ProducerFoodClass(food)).start();
        new Thread(new ProducerFoodClass(food)).start();
        new Thread(new ConsumerFoodClass(food)).start();
        new Thread(new ConsumerFoodClass(food)).start();
    }


    static class ProducerFoodClass implements Runnable {
        private Food food;

        public ProducerFoodClass(Food food) {
            this.food = food;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    food.producerFood();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ConsumerFoodClass implements Runnable {
        private Food food;

        public ConsumerFoodClass(Food food) {
            this.food = food;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    food.consumerFood();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class Food {

    private static Lock lock = new ReentrantLock();
    private Condition proCondition = lock.newCondition();
    private Condition conCondition = lock.newCondition();
    private List<String> list = new ArrayList<>();

    /**
     * 生产
     */
    public void producerFood() throws InterruptedException {
        lock.lock();
        while (list.size() >= 20) {
            proCondition.await();
        }
        list.add("Food" + list.size());
        System.out.println(Thread.currentThread().getName() + "生产" + list.size());
        conCondition.signalAll();
        lock.unlock();
    }

    /**
     * 消费
     */
    public void consumerFood() throws InterruptedException {
        lock.lock();
        while (list.size() <= 0) {
            conCondition.await();
        }
        System.out.println(Thread.currentThread().getName() + "消费" + list.size());
        list.remove(0);
        proCondition.signalAll();
        lock.unlock();
    }
}
