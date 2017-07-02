package com.notification.service.dequeue;

import com.notification.service.constants.ClientNotificationPreferences;
import com.notification.service.kafka.KafkaConsumerGroup;
import com.notification.service.redis.RedisConsumerGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Aakash on 31/05/17.
 */
@Component
public class NotificationProcessor {

    @Autowired
    private KafkaConsumerGroup kafkaConsumerGroup;

    @Autowired
    private RedisConsumerGroup redisConsumerGroup;

    public void processNotification(){
        ClientNotificationPreferences clientPreference = ClientNotificationPreferences.REDIS;

        if(clientPreference.equals(ClientNotificationPreferences.KAFKA)){
            kafkaConsumerGroup.initiateKafkaConsumer();
        } else{
            redisConsumerGroup.initiateRedisNotifier();
        }
    }
}
