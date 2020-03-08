package com.ql;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2020-03-03 02:12
 * @Version 1.0
 **/
public class ConcurrentHashMapTests {

    public static void main(String[] args) {
        System.out.println("main start");
        Map<String,Integer> map = new ConcurrentHashMap<String, Integer>();
        map.put("1",1);
        map.get("1");
        System.out.println("main end");

    }
}
