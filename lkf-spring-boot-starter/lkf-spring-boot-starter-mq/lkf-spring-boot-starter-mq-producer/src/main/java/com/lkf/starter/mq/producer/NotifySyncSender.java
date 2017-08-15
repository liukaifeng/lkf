package com.lkf.starter.mq.producer;


import com.lkf.starter.mq.config.RabbitMqConstant;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by ryq on 2017/6/6.
 */
@Component
public class NotifySyncSender<T extends Serializable> extends CommonBusMessageProducer<T> {

    @Override
    public String queue() {
        return RabbitMqConstant.Queue.QUEUE_NOTIFY;
    }
}
