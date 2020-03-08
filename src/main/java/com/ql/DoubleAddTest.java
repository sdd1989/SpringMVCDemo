package com.ql;

import java.math.BigDecimal;

/**
 * @Description double相加不准确,用bigdecimal代替
 * @Author qiuliang
 * @Time 2019-11-11 16:29
 * @Version 1.0
 **/
public class DoubleAddTest {


    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(0.0d);
        for(int i=0;i<3;i++){
            b = b.add(BigDecimal.valueOf(10763.7d));
        }
        System.out.println(b.doubleValue());
    }
}
