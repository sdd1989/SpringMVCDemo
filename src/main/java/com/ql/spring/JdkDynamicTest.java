package com.ql.spring;

import java.lang.reflect.Proxy;

public class JdkDynamicTest {

    public static void main(String[] args) {
        TestServiceI proxy = (TestServiceI) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                ,TestService.class.getInterfaces(),new TestServiceInvocationHandler(new TestService()));
        proxy.test();

    }
}
