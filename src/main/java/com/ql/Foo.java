package com.ql;

import lombok.*;
import org.springframework.beans.BeanUtils;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-11-27 10:43
 * @Version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Foo {

    private String a;

    private Integer b;

    public static void main(String[] args) {
//        Foo foo = new Foo("a",1);
//        Foo2 foo2 = new Foo2();
//        BeanUtils.copyProperties(foo,foo2);
//        System.out.println(foo2);

        Foo2 foo2 = new Foo2("a",1,"b");
        Foo foo = new Foo();
        BeanUtils.copyProperties(foo2,foo);
        System.out.println(foo);
    }
}
