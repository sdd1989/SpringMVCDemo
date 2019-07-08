package com.ql.thread;

import com.alibaba.fastjson.JSON;
import com.sankuai.meituan.waimai.async.AsyncFrameworkFactory;
import com.sankuai.meituan.waimai.async.containor.CommonCallback;

public class TestAsyncFramework {

    public static void main(String[] args) {
        for(;;) {
            CommonCallback testCallback = new CommonCallback<Object>() {
                @Override
                public void onSuccess(Object o) {
                    System.out.println("onSuccess ...");
                }

                @Override
                public void onFail(Exception e) {
                    System.out.println("onFail ...");
                }

                @Override
                public Object call() {
                    System.out.println("call ...");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                public String toString(){
                    return ""+this.getExpire();
                }

            };
            System.out.println("testCallback:"+testCallback);
            AsyncFrameworkFactory.getInstance().submit(testCallback);
            System.out.println("AsyncFrameworkFactory:"+JSON.toJSONString(AsyncFrameworkFactory.getInstance()));
        }
    }
}
