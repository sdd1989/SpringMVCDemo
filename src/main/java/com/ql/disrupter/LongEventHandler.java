package com.ql.disrupter;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO:
 * @Author qiuliang
 * @Time 2019-12-19 10:02
 * @Version 1.0
 **/
@Slf4j
public class LongEventHandler implements EventHandler<LongEvent>
{
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        log.info("############start################# ");
        log.info("Event: " + event);
        log.info("sequence: " + sequence);
        log.info("endOfBatch: " + endOfBatch);
        log.info("############end################# ");
    }
}