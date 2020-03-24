package com.ql.sync;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2020-03-10 09:32
 * @Version 1.0
 **/
public class SynchronizedTests {

    public static void main(String[] args) {
        A a = new A();
        new Thread(
                () -> new A().method1()
        ).start();
        new Thread(
                () -> new A().method2()
        ).start();
    }
}

class A{

    public static synchronized void  method1(){
        System.out.println("method1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void  method2(){
        System.out.println("method2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
