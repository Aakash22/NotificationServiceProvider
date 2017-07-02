package com.notification.service.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by Aakash on 19/06/17.
 */
public class KafkaProducer {

    private org.apache.kafka.clients.producer.KafkaProducer kafkaProducer;

    public void initKafkaProducer() {

        Properties producerProps = new Properties();
        producerProps.put("bootstrap.servers","localhost:9092");
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
        producerProps.put("acks", "1");
        producerProps.put("request.timeout.ms", 1000);
        producerProps.put("timeout.ms", 500);
        kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer(producerProps);

    }


    public void pushMessage(String topic, String message) {
        try {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, message);
            kafkaProducer.send(producerRecord);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
