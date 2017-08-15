package com.lkf.starter.mq.config;

/**
 * Created by Administrator on 5月16日.
 */
public class RabbitMqConstant {

    public static class Exchange{
        //交换机：总线
        public static final String EXCHANGE_COMMON = "lkf-common-bus";
    }

    public static class Queue{
        //日志队列
        public static final String QUEUE_LOG="lkf.log";
        //消息队列
        public static final String QUEUE_MESSAGE="lkf.message";
        //通知队列
        public static final String QUEUE_NOTIFY="lkf.notify";
    }

}
