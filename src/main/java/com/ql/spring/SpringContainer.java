package com.ql.spring;

import com.google.common.collect.Lists;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpringContainer implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        System.out.println("onApplicationEvent");
        List<ProductBeanDef> productBeanDefList = readConfig();
        for(ProductBeanDef productBeanDef : productBeanDefList){
            registerBean(productBeanDef);
        }

    }

    private List<ProductBeanDef> readConfig() {
        List<ProductBeanDef> productBeanDefs = Lists.newArrayList();
        for(int i=1;i<=3;i++){
            ProductBeanDef productBeanDef = new ProductBeanDef(ProductFactoryBean.class);
            MutablePropertyValues propertyValues = new MutablePropertyValues();
            propertyValues.add(ProductBeanDef.PROP_NAME_PRODUCT_NAME,"name"+i);
            propertyValues.add(ProductBeanDef.PROP_NAME_PRODUCT_ID,"id"+i);
            productBeanDef.setPropertyValues(propertyValues);
            productBeanDefs.add(productBeanDef);
        }
        return productBeanDefs;
    }

    private void registerBean(ProductBeanDef productBeanDef) {
        MutablePropertyValues props = productBeanDef.getPropertyValues();
        String id = (String) props.get(ProductBeanDef.PROP_NAME_PRODUCT_ID);
        SpringContextUtil.registerBeanDef(id, productBeanDef);
    }
}
