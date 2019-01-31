package com.ql;

import com.ql.springretry.RetryDemoService;
import com.ql.springretry.RetryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestCase {

    @Autowired
    private RetryDemoService retryDemoService;

    @Autowired
    private RetryService retryService;

    @Test
    public void test(){
        try {
            retryDemoService.doDemo("a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        int count = retryService.retry(-1);
        System.out.println("库存为 ：" + count);
    }
}
