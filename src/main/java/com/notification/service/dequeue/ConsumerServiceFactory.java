package com.notification.service.dequeue;

import com.notification.service.constants.QueueTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aakash on 17/06/17.
 */
@Service
public class ConsumerServiceFactory {

    @Autowired
    private NotificationConsumer notificationConsumer;

    public ConsumerService getConsumerService(String topic){

        QueueTopic queueTopic = QueueTopic.fromString(topic);

        switch (queueTopic){
            case SMS_EMAIL_NOTIFICATION: return notificationConsumer;
        }

        return null;
    }
}
