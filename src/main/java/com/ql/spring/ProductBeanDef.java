package com.ql.spring;

import lombok.Data;
import org.springframework.beans.factory.support.RootBeanDefinition;


@Data
public class ProductBeanDef extends RootBeanDefinition {

    public static final String PROP_NAME_PRODUCT_ID = "productId";
    public static final String PROP_NAME_PRODUCT_NAME = "productName";

    public ProductBeanDef(Class<?> beanClass) {
        super(beanClass);
    }

}