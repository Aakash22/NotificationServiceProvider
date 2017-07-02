package com.notification.service.redis;

import com.notification.service.dequeue.ConsumerServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aakash on 03/06/17.
 */
@Component
public class RedisConsumerGroup {
    private static final Logger logger = LoggerFactory.getLogger(RedisConsumerGroup.class);

    @Autowired
    private RedisServiceManager redisServiceManager;

    @Autowired
    private Environment env;

    @Autowired
    private ConsumerServiceFactory consumerServiceFactory;

    public void initiateRedisNotifier(){
        List<String> topicList = Arrays.asList(env.getRequiredProperty("kafka.topics").split(","));
        ExecutorService executor = Executors.newFixedThreadPool(topicList.size());

        for(String topic : topicList) {
            RedisConsumerThread redisConsumerThread = new RedisConsumerThread(topic, consumerServiceFactory,redisServiceManager);
            executor.submit(redisConsumerThread);
        }
    }
}
