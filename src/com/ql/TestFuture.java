package com.ql;

import java.util.concurrent.*;

public class TestFuture {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);


    public static void main(String[] args) {

        Future<String> feture = executorService.submit(new Callable<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "ok";
            }
        });

        try {
            feture.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        executorService.isShutdown();

    }
}
