package com.ql.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPool {


    private static ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                System.out.println("run ...");
            }
        },1, TimeUnit.SECONDS);
    }
}
