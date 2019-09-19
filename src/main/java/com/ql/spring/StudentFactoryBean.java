package com.ql.spring;

import org.springframework.beans.factory.FactoryBean;

public class StudentFactoryBean implements FactoryBean<Student> {

    @Override
    public Student getObject() throws Exception {
        return Student.builder().id(111111).name("sdd").build();
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
