package com.notification.service.enqueue;

import com.notification.service.constants.ClientNotificationPreferences;
import com.notification.service.constants.QueueTopic;
import com.notification.service.kafka.KafkaProducer;
import com.notification.service.redis.RedisServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Created by Aakash on 19/06/17.
 */
public class EnqueueDataService {

    @Autowired
    private Environment env;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private RedisServiceManager redisServiceManager;

    public void initiateQueuingDataService(ClientNotificationPreferences clientNotificationPreferences){
        if(clientNotificationPreferences.equals(ClientNotificationPreferences.KAFKA)){
            kafkaProducer.initKafkaProducer();
        }
    }

    public void queuingDataService(ClientNotificationPreferences clientNotificationPreferences, QueueTopic topic, String payload){
        if(clientNotificationPreferences.equals(ClientNotificationPreferences.KAFKA)){
            kafkaProducer.pushMessage(topic.getValue(),payload);
        }
        else{
            redisServiceManager.lpush(topic.getValue(), payload);
        }
    }
}
