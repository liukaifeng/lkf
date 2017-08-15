package com.lkf.starter.mq.producer;

import java.io.Serializable;

/**
 * Created by Administrator on 5月16日.
 */
public interface MessageProducer<T extends Serializable> {


    public void produce(T t);
}
