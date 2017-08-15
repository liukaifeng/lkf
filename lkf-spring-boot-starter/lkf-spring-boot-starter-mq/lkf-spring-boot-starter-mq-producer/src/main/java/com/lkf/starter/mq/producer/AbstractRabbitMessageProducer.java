package com.lkf.starter.mq.producer;


import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by Administrator on 5月16日.
 */
public abstract class AbstractRabbitMessageProducer<T extends Serializable> implements MessageProducer <T>{

    @Autowired
    protected AmqpTemplate rabbitTemplate;

    @Override
    public void produce(T t) {
        this.rabbitTemplate.convertAndSend(exchange(),queue(), JSONObject.toJSONString(t));
    }

    public abstract String exchange();

    public abstract String queue();


}
