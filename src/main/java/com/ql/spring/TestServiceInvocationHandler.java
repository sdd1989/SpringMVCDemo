package com.ql.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestServiceInvocationHandler implements InvocationHandler {

    private Object target;

    public TestServiceInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy start ...");
        method.invoke(target,args);
        System.out.println("proxy end ...");
        return proxy;
    }

}
