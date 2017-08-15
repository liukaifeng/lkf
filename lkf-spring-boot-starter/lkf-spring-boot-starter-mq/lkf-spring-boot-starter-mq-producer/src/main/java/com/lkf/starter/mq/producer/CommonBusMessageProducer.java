package com.lkf.starter.mq.producer;


import com.lkf.starter.mq.config.RabbitMqConstant;

import java.io.Serializable;

public abstract class CommonBusMessageProducer<T extends Serializable> extends AbstractRabbitMessageProducer<T> {

    @Override
    public final String exchange() {
        return RabbitMqConstant.Exchange.EXCHANGE_COMMON;
    }

}
