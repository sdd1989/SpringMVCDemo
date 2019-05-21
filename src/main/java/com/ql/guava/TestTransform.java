package com.ql.guava;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.List;

public class TestTransform {

    public static void main(String[] args) {
        List<Foo> foos = Lists.newArrayList();
        for(int i=0;i<10;i++){
            Foo foo = new Foo();
            foo.setAge(i);
            foo.setName("name"+i);
            foos.add(foo);
        }
        List<Foo2> fooList = Lists.transform(foos, new Function<Foo, Foo2>() {
            @Nullable
            @Override
            public Foo2 apply(@Nullable Foo input) {
                Foo2 foo = new Foo2();
                foo.setName(input.getName()+"transfer");
                return foo;
            }
        });
//        fooList = Lists.partition(fooList,10).get(0);
        System.out.println(fooList.getClass());
//        fooList = Lists.newArrayList(fooList);
        fooList = ImmutableList.copyOf(fooList);
        System.out.println(fooList.getClass());
//        System.out.println(fooList);
        for(Foo2 foo:fooList){
            foo.setName(foo.getName()+"update");
        }
        System.out.println(fooList);

    }
}
class Foo{

    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Foo2{

    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
