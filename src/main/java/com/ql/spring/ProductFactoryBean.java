package com.ql.spring;

import org.springframework.beans.factory.FactoryBean;

public class ProductFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        Product product =  new Product();
        product.setProductId("1111111");
        product.setProductName("xxxxxxx");
        return product;
    }

    @Override
    public Class<?> getObjectType() {
        return Product.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
