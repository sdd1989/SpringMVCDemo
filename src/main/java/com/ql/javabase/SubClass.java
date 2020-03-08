package com.ql.javabase;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-11-28 20:08
 * @Version 1.0
 **/
@Slf4j
public class SubClass extends BaseClass{

    private String name = "subname";

    public String getName() {
        return this.name;
    }

    public String printName() {
        return this.name + super.name;
    }

    public static void main(String[] args) {
        SubClass sub = new SubClass();
        System.out.println(sub.printName());
    }
}
