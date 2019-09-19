package com.ql.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDynamicResgistTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        for(int i=1;i<=3;i++) {
            Product product = (Product) applicationContext.getBean("id"+i);
            System.out.println(product);
        }
    }
}
