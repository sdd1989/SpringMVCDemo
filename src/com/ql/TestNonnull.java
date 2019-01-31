package com.ql;

import java.util.ArrayList;

public class TestNonnull {

    public void foo( Object o){
        int i = o.hashCode();
    }

    public void call(ArrayList list){
        foo(list);
    }
}
