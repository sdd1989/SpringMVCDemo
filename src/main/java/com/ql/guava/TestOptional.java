package com.ql.guava;


import com.google.common.base.Optional;

public class TestOptional {

    public static void main(String[] args) {

//        Optional<Too> optional = Optional.fromNullable(new Too(1));
        Optional<Too> optional = Optional.fromNullable(null);
        System.out.println(optional);
        Too too = optional.or(new Too(2));
        System.out.println(too);
    }
}
