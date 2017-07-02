package com.notification.service.redis;

import com.notification.service.dequeue.ConsumerService;
import com.notification.service.dequeue.ConsumerServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aakash on 17/06/17.
 */
public class RedisConsumerThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(RedisConsumerThread.class);

    private String topic;
    private ConsumerService consumerService;
    private RedisServiceManager redisServiceManager;

    public RedisConsumerThread(String topic, ConsumerServiceFactory consumerServiceFactory,RedisServiceManager redisServiceManager){
        this.topic = topic;
        this.consumerService = consumerServiceFactory.getConsumerService(topic);
        this.redisServiceManager = redisServiceManager;
    }

    public void run() {
        while(true){
            String payload = redisServiceManager.rpop(topic);
            if(StringUtils.isNotBlank(payload)){
                consumerService.consumePayload(payload);
            }
        }
    }
}
