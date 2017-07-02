package com.notification.service.constants;

/**
 * Created by Aakash on 19/06/17.
 */
public enum QueueTopic {

    SMS_EMAIL_NOTIFICATION("notify");

    private final String kafkaTopic;

    private QueueTopic(String topic) {
        this.kafkaTopic = topic;
    }
    public  String getValue() {
        return kafkaTopic;
    }

    public static QueueTopic fromString(String topic) {

        for(QueueTopic value : values()) {
            if(value.getValue().equals(topic)) {
                return value;
            }
        }

        return null;
    }
}
