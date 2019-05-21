package com.ql;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TestGuava {

    public static void main(String[] args) {
        List list = Lists.transform(new ArrayList<>(), new Function<Object, Object>() {
            @Nullable
            @Override
            public Object apply(@Nullable Object input) {
                return null;
            }
        });
        System.out.println("list:"+list);
    }
}
