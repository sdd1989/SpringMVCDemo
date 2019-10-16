package com.ql.thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestThreadState {


    @Test
    public void testNew() {
        Thread myThread = new Thread(){
            @Override
            public void run(){
                super.run();
            }
        };
        System.out.println("current Thread: " + Thread.currentThread().getName() + " isAlive: " + Thread.currentThread().isAlive());
        System.out.println("myThread: " + myThread.getName() + " isAlive: " + myThread.isAlive());
        System.out.println("myThread: " + myThread.getName() + " state: " + myThread.getState());
    }

    @Test
    public void testTerminated() throws InterruptedException {
        Thread myThread = new Thread(){
            @Override
            public void run(){
                super.run();
            }
        };
        myThread.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("myThread: " + myThread.getName() + " isAlive: " + myThread.isAlive());
        System.out.println("myThread: " + myThread.getName() + " state: " + myThread.getState());
    }

    @Test
    public void testRunnable(){
        System.out.println("current Thread name: " + Thread.currentThread().getName() + " isAlive: " + Thread.currentThread().isAlive());
        System.out.println("current Thread name: " + Thread.currentThread().getName() + " state: " + Thread.currentThread().getState());
        System.out.println("main end");

    }

    @Test
    public void testTimeWait() throws InterruptedException {
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
        System.out.println("myThread: " + myThread.getName() + " isAlive: " + myThread.isAlive());
        System.out.println("myThread: " + myThread.getName() + " state: " + myThread.getState());
    }

    private Object object = new Object();

    @Test
    public void testWait() throws InterruptedException {
        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (object) {
                        object.wait();
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
        Thread.sleep(100000);
        System.out.println("myThread: " + myThread.getName() + " isAlive: " + myThread.isAlive());
        System.out.println("myThread: " + myThread.getName() + " state: " + myThread.getState());
    }

    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(100);

    @Test
    public void testBlock() throws InterruptedException {
        Thread producer = new Thread(){
            @Override
            public void run(){
                poduce();
            }
        };
        producer.start();
        Thread consumer = new Thread(){
            @Override
            public void run(){
                consume();
            }
        };
        consumer.start();
        while (true) {
            Thread.sleep(1000);
//            System.out.println("producer: " + producer.getName() + " isAlive: " + producer.isAlive());
            System.out.println("producer: " + producer.getName() + " state: " + producer.getState());
//            System.out.println("consumer: " + consumer.getName() + " isAlive: " + consumer.isAlive());
            System.out.println("consumer: " + consumer.getName() + " state: " + consumer.getState());
        }

    }

    private void consume(){
        try {
            while (true) {
                synchronized (object) {
                    if (queue.size() == 0) {
                        System.out.println("queue empty waiting");
                        object.wait();
                    } else {
                        Integer element = queue.poll();
                        System.out.println("consumer element : " + element);
                        Thread.sleep(100);
                        object.notify();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void poduce(){
        try {
            while (true) {
                synchronized (object) {
                    if (queue.size() >= 100) {
                        System.out.println("queue full waiting");
                        object.wait();
                    } else {
                        Integer element = new Random().nextInt(100);
                        System.out.println("produce element : " + element);
                        queue.add(element);
                        Thread.sleep(100);
                        object.notify();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
