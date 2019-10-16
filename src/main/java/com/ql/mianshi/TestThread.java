package com.ql.mianshi;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    private static int i = 1;
    private volatile static int j = 1;
//    public volatile static boolean flag = false;
    public boolean flag = false;
    private static int count = 1000000;

    private static Lock lock = new ReentrantLock();

    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();

    private static CountDownLatch latch = new CountDownLatch(2);
    private static AtomicInteger numA = new AtomicInteger();
    private static AtomicInteger numB = new AtomicInteger();

    private static byte[] block = new byte[0];

    @Test
    public void test1() {
        new Thread(() -> {
            while (i < count + 1) {
                synchronized (block) {
                    if (flag) {
                        try {
                            block.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()
                                + "----" + (i++));
                        flag = true;
                        block.notifyAll();
                    }

                }
            }
        }).start();

        new Thread(() -> {
            while (i < count + 1) {
                synchronized (block) {

                    if (!flag) {
                        try {
                            block.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName()
                                + "----" + (i++));
                        flag = false;
                        block.notifyAll();
                    }

                }
            }
        }).start();
    }

    public static void main(String[] args) {
//        test1();
    }

}
