package com.lkf.starter.mq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig{

    @Bean
    public TopicExchange initExchange() {
        return new TopicExchange(RabbitMqConstant.Exchange.EXCHANGE_COMMON);
    }

    @Bean(name = "mq_queue_log")
    public Queue queueMessageLog() {
        return new Queue(RabbitMqConstant.Queue.QUEUE_LOG);
    }
    @Bean(name = "mq_queue_message")
    public Queue queueMessage() {
        return new Queue(RabbitMqConstant.Queue.QUEUE_MESSAGE);
    }
    @Bean(name = "mq_queue_notify")
    public Queue queueMessageNotify() {
        return new Queue(RabbitMqConstant.Queue.QUEUE_NOTIFY);
    }

    @Bean
    public Binding bindingExchangeMessageLog(@Qualifier("mq_queue_log") Queue queueMessageLog, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessageLog).to(exchange).with(RabbitMqConstant.Queue.QUEUE_LOG+".#");
    }

    @Bean
    public Binding bindingExchangeMessage(@Qualifier("mq_queue_message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(RabbitMqConstant.Queue.QUEUE_MESSAGE+".#");
    }

    @Bean
    public Binding bindingExchangeMessageNotify(@Qualifier("mq_queue_notify") Queue queueMessageNotify, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessageNotify).to(exchange).with(RabbitMqConstant.Queue.QUEUE_NOTIFY+".#");
    }

}