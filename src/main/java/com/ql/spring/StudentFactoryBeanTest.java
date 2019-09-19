package com.ql.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentFactoryBeanTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Student student = (Student) applicationContext.getBean("studentFactoryBean");
        System.out.println(student);
    }
}
