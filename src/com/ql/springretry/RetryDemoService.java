package com.ql.springretry;

import org.apache.log4j.Logger;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class RetryDemoService {

    Logger logger = Logger.getLogger(getClass());


    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public void doDemo(String testStr) throws Exception {
        logger.info("doDemo");
        try {
            if (testStr.equals("a")) {
                throw new Exception();
            }
        }catch (Exception e){
            logger.error("error");
        }

    }

    @Recover
    public void recover(Exception e) {
        logger.error("重试机制3次完成，结果依然异常..");
    }
}
