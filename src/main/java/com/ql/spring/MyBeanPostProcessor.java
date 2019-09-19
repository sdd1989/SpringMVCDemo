package com.ql.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        super();
        System.out.println("BeanPostProcessor构造函数");
    }

    public Object postProcessAfterInitialization(Object arg0, String arg1)
            throws BeansException {
        //可以针对指定的Bean做一些操作
        if (arg1.equals("car")) {
            System.out.println("BeanPostProcessor.postProcessAfterInitialization()");
        }
        return arg0;
    }

    public Object postProcessBeforeInitialization(Object arg0, String arg1)
            throws BeansException {
        if (arg1.equals("car")) {
            System.out.println("BeanPostProcessor.postProcessBeforeInitialization()");
        }
        return arg0;
    }
}

