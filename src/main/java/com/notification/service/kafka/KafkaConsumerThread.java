package com.notification.service.kafka;

import com.notification.service.dequeue.ConsumerService;
import com.notification.service.dequeue.ConsumerServiceFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Aakash on 17/06/17.
 */
public class KafkaConsumerThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerThread.class);

    private KafkaConsumer<String, String> kafkaConsumer;
    private String topic;
    private ConsumerService consumerService;

    public KafkaConsumerThread(KafkaConsumer<String, String> kafkaConsumer, String topic, ConsumerServiceFactory consumerServiceFactory){
        this.kafkaConsumer =kafkaConsumer;
        this.topic = topic;
        this.consumerService = consumerServiceFactory.getConsumerService(topic);
    }

    public void run() {
        try {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Long.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records) {
                String payload = record.value();
                consumerService.consumePayload(payload);
            }
        }catch (Exception e) {
            logger.error("Exception occurred {}",e);
        } finally {
            kafkaConsumer.close();
        }
    }
}
