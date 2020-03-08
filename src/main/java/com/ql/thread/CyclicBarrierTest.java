package com.ql.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2020-03-05 11:06
 * @Version 1.0
 **/
public class CyclicBarrierTest {

    /**
     * 写一个20个线程在某一固定时刻并发开始对同一个数值进行计数到100结束
     * @param args
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("all thread start!");
            }
        });
        for(int i=0;i<20;i++){
            new Thread(new CyclicBarrierTask(atomicInteger,cyclicBarrier)).start();
        }
    }
}
class CyclicBarrierTask implements Runnable{

    private AtomicInteger atomicInteger;

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierTask(AtomicInteger atomicInteger,CyclicBarrier cyclicBarrier) {
        this.atomicInteger = atomicInteger;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        for(int i=0;i<5;i++){
            atomicInteger.incrementAndGet();
        }

    }
}


