package com.lkf.consumer.notify.listener;


import com.lkf.consumer.notify.service.NotifyService;
import com.lkf.starter.mq.config.RabbitMqConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ryq on 2017.06.06.
 */
@Component
@RabbitListener(queues = RabbitMqConstant.Queue.QUEUE_NOTIFY)
public class NotifyConsumer {

    @Autowired
    private NotifyService notifyService;

    @RabbitHandler
    public void onMessage(String NotifyInfoJson){
        notifyService.saveNotify(NotifyInfoJson);
    }

}
