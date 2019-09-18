package com.ql.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Product product = (Product) applicationContext.getBean("productFactoryBean");
        System.out.println(product);

    }
}
