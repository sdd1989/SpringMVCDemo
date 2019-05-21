package com.ql.testcase;

import com.ql.HttpClientUtil;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static ExecutorService executorService = Executors.newFixedThreadPool(10);

    static String[] wdcIds = {"77109914","2539434","1000903039","79488328"};

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
//                    String url = "http://10.21.61.215:8912/qb/restful/api/v3/wmpoi/r/baseinfo/frontsurround?wdcId=1000903039";
                    String url = "http://10.21.61.215:8912/qb/restful/api/v3/wmpoi/r/baseinfo/frontsurround?wdcId="+wdcIds[new Random().nextInt(4)];
                    HttpClientUtil.get(url);
                    System.out.println("耗时:"+ (System.currentTimeMillis() - start)+"ms");
                }
            });
            Thread.sleep(10);
        }
    }

}
