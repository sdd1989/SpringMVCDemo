package com.ql.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class Car implements BeanFactoryAware, BeanNameAware,
        InitializingBean, DisposableBean {
    private String carName;
    private BeanFactory beanFactory;
    private String beanName;

    public Car() {
        System.out.println("bean的构造函数");
    }

    public void setCarName(String carName) {
        System.out.println("属性注入");
        this.carName = carName;
    }

    // 这是BeanFactoryAware接口方法
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out
                .println("BeanFactoryAware.setBeanFactory()");
        this.beanFactory = arg0;
    }

    // 这是BeanNameAware接口方法
    public void setBeanName(String arg0) {
        System.out.println("BeanNameAware.setBeanName()");
        this.beanName = arg0;
    }

    // 这是InitializingBean接口方法
    public void afterPropertiesSet() throws Exception {
        System.out
                .println("InitializingBean.afterPropertiesSet()");
    }

    // 这是DiposibleBean接口方法
    public void destroy() throws Exception {
        System.out.println("DiposibleBean.destory()");
    }

    // 通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("<bean>的init-method属性指定的初始化方法");
    }

    // 通过<bean>的destroy-method属性指定的初始化方法
    public void myDestory() {
        System.out.println("<bean>的destroy-method属性指定的初始化方法");
    }
}