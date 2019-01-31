package com.ql.springretry;

import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.retry.*;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.CircuitBreakerRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;

/**
 * https://forgkan.gitee.io/2018/07/01/java/spring-retry/
 */
public class SpringRetryTestCase {
    public static int i = 0;
    public static void main(String[] args) {
        //1.无状态重试
//        statusRetry();
        //2.有状态重试
//        statuslessRetry();
        //3.熔断器场景
        circuitRetry();

    }

    private static void circuitRetry() {
        RetryTemplate template = new RetryTemplate();
        CircuitBreakerRetryPolicy retryPolicy =
                new CircuitBreakerRetryPolicy(new SimpleRetryPolicy(3));
        retryPolicy.setOpenTimeout(5000);
        retryPolicy.setResetTimeout(20000);
        template.setRetryPolicy(retryPolicy);
        Object key = "circuit";
        boolean isForceRefresh = false;
        RetryState state = new DefaultRetryState(key, isForceRefresh);

        for (int i = 0; i < 10; i++) {
            try {
                String result = template.execute(new RetryCallback<String, RuntimeException>() {
                    @Override
                    public String doWithRetry(RetryContext context) throws RuntimeException {
                        System.out.println("retry count:" + context.getRetryCount());
                        throw new RuntimeException("timeout");
                    }
                }, new RecoveryCallback<String>() {
                    @Override
                    public String recover(RetryContext context) throws Exception {
                        return "default";
                    }
                }, state);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    private static void statuslessRetry() {
        RetryTemplate template = new RetryTemplate();
        //当前状态的名称，当把状态放入缓存时，通过该key查询获取
        Object key = "mykey";
        //是否每次都重新生成上下文还是从缓存中查询，即全局模式（如熔断器策略时从缓存中查询）
        boolean isForceRefresh = true;
        //对DataAccessException进行回滚
        BinaryExceptionClassifier rollbackClassifier =
                new BinaryExceptionClassifier(Collections.<Class<? extends Throwable>>singleton(DataAccessException.class));
        RetryState state = new DefaultRetryState(key, isForceRefresh, rollbackClassifier);

        String result = template.execute(new RetryCallback<String, RuntimeException>() {
            @Override
            public String doWithRetry(RetryContext context) throws RuntimeException {
                System.out.println("retry count:" + context.getRetryCount());
                throw new TypeMismatchDataAccessException("");
            }
        }, new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) throws Exception {
                return "default";
            }
        }, state);
        System.out.println(result);
    }

    private static void statusRetry() {
        RetryTemplate template = new RetryTemplate();

        RetryCallback<String, RuntimeException> retryCallback = context -> {
            i++ ;
            if( i < 3){
                if(i%2 == 0){
                    System.out.println("in fuck");
                    throw new IllegalArgumentException("fuck");
                }
                System.out.println("in no");
                throw new IllegalStateException("no");
            }
            System.out.println("normal return");
            return "hello";
        };

        // 恢复策略
        RecoveryCallback<String> recoveryCallback = context -> {System.out.println("in recovery.."); return "recovery";};
        // 设置回避策略
        template.setBackOffPolicy(new FixedBackOffPolicy());
        // 设置策略
        template.setRetryPolicy(new SimpleRetryPolicy(5));
        // 设置listener
        template.setListeners(new RetryListener[]{new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {

                System.out.println("RetryListener-open");
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback,
                                                       Throwable throwable) {
                System.out.println("RetryListener-close");
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
                                                         Throwable throwable) {
                System.out.println("RetryListener-onError");
            }
        }});
        // 执行模板
        String word = template.execute(retryCallback, recoveryCallback);

        System.out.println(word);
    }
}
