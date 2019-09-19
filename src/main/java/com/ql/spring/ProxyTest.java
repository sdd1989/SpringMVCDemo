package com.ql.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        TestServiceI testService = (TestServiceI) applicationContext.getBean("testServiceProxy");
        testService.test();
    }
}
