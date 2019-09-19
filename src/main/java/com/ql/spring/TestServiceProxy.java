package com.ql.spring;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

@Data
public class TestServiceProxy implements FactoryBean<Object> {

    private Object proxy;


    @Override
    public Object getObject() throws Exception {
        proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader()
                ,TestService.class.getInterfaces(),new TestServiceInvocationHandler(new TestService()));
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return TestService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}


