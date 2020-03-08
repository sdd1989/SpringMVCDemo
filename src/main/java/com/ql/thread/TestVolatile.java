package com.ql.thread;

public class TestVolatile {
        int a = 1;
        int b = 2;

        public void change(){
            a = 3;
            b = a;
        }

        public void print(){
            System.out.println("b="+b+";a="+a);
            if(b == 3 && a == 1){
                System.err.println("b="+b+";a="+a);
                System.exit(-1);
            }
        }

        public static void main(String[] args) {
            while (true){
                final TestVolatile test = new TestVolatile();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        test.change();
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        test.print();
                    }
                }).start();

            }
        }
}
