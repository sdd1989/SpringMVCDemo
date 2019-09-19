package com.ql.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static Object getBean(String name) {
        return CONTEXT.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return CONTEXT.getBean(name, requiredType);
    }

    public static void registerBeanDef(String name, BeanDefinition beanDefinition) {
        getBeanFactory().registerBeanDefinition(name, beanDefinition);
    }

    public static void removeBeanDef(String name) {
        getBeanFactory().removeBeanDefinition(name);
    }

    private static DefaultListableBeanFactory getBeanFactory() {
        return (DefaultListableBeanFactory) CONTEXT.getAutowireCapableBeanFactory();
    }


}
