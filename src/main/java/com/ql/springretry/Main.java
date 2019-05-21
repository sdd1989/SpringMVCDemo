package com.ql.springretry;

import com.alibaba.fastjson.JSON;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryState;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.stats.DefaultStatisticsRepository;
import org.springframework.retry.stats.StatisticsListener;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

public class Main {

    private static int n = 0;

    public static void main(String[] args) throws Throwable {
        // 新建一个模板，可用作为我一个spring bean注入进来
        RetryTemplate template = new RetryTemplate();
        RetryCallback<String, Throwable> retryCallback = context -> remoteInvoke();
        RecoveryCallback<String> recoveryCallback = context -> {System.out.println(JSON.toJSONString(context,true));System.out.println("recovery"); return "recovery";};
        // 设置回避策略
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000);
        template.setBackOffPolicy(backOffPolicy);
        // 设置策略
        template.setRetryPolicy(new SimpleRetryPolicy(3));
        // 设置listener
        template.setListeners(new RetryListener[]{new StatisticsListener(new DefaultStatisticsRepository())});
//        // 执行模板
//        try {
//            String result = template.execute(retryCallback, recoveryCallback);
//            System.out.println("result = "+result);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //有状态重试，有两种情况需要使用有状态重试:事务操作需要回滚或者熔断器模式。
        BinaryExceptionClassifier classifier = new BinaryExceptionClassifier(
                Collections.singleton(MyException.class)
        );
        RetryState state = new DefaultRetryState("mykey", false, classifier);
        String result = null;
        try {
            // 有状态
            result = template.execute(retryCallback,recoveryCallback , state);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("result = " + result);
    }

    private static String remoteInvoke() throws Exception {
        System.out.println("invoke remoteInvoke");
//        if(n==0 || n==1){
//            n++;
        if(1==1){
            throw new MyException();
        }
        return "success";
    }


}
