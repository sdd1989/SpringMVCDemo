package com.ql.spring;


import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
@Data
public class ProductFactoryBean implements FactoryBean<Product> {

    private String productId;
    private String productName;

    @Override
    public Product getObject() throws Exception {
        System.out.println("getObject");
        Product product = new Product();
        product.setProductId(this.productId);
        product.setProductName(this.productName);
        return product;
    }

    @Override
    public Class<?> getObjectType() {
        return ProductBeanDef.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
