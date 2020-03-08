package com.ql.disrupter;

import lombok.ToString;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-12-18 21:30
 * @Version 1.0
 **/
@ToString
public class LongEvent
{
    private long value;

    public void set(long value)
    {
        this.value = value;
    }
}
