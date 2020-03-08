package com.ql.disrupter;

import com.lmax.disruptor.EventFactory;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-12-18 21:30
 * @Version 1.0
 **/
public class LongEventFactory implements EventFactory<LongEvent>
{
    @Override
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}
