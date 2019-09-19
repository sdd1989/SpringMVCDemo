package com.ql.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExtendPointTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        factory.destroy();
    }
}
