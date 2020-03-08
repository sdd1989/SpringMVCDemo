package com.ql.thread;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-11-13 23:16
 * @Version 1.0
 **/
class Singleton{
    private /** volatile */ static Singleton instance = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if(instance==null) {
            synchronized (Singleton.class) {
                if(instance==null)
                    instance = new Singleton();
            }
        }
        return instance;
    }


    public static void main(String[] args) {

        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton singleton = Singleton.getInstance();
                    System.out.println(singleton);
                }
            }).start();
        }

    }
}
