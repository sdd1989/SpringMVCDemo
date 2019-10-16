package com.ql.guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

public class TestSupplier {

    public static void main(String[] args) throws InterruptedException {
        Supplier<Integer> memoize = Suppliers.memoize(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("init supplier wrapped object");
                return 1;
            }
        });
        System.out.println(memoize.get());
        System.out.println(memoize.get());

//        Supplier<Integer> memoize = Suppliers.memoize(new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("init supplier wrapped object");
//                return 1;
//            }
//        });
//        System.out.println("main thread block");
//        Thread.sleep(2000);
//        System.out.println(memoize.get());
    }
}
