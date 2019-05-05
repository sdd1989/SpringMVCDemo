package com.ql.ddd;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class Test {


    @org.junit.Test
    public void test(){
        IdCard idCard = IdCard.Factory.make();
        idCard.write();
        IdCard idCard2 = IdCard.Factory.make();
        idCard2.write();
        IdCard idCard3 = IdCard.Factory.make();
        idCard3.write();
        System.out.println("idCard:"+idCard);
        System.out.println("idCard2:"+idCard2);
        System.out.println("idCard3:"+idCard3);
    }

}
