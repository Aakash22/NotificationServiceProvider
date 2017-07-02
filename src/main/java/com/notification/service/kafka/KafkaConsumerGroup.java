package com.notification.service.kafka;

import com.notification.service.dequeue.ConsumerServiceFactory;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aakash on 31/05/17.
 */
@Component
public class KafkaConsumerGroup {

    @Autowired
    private Environment env;

    @Autowired
    private ConsumerServiceFactory consumerServiceFactory;

    public void initiateKafkaConsumer() {
        KafkaConsumer<String, String> kafkaConsumer = null;

        try {
            Properties consumerProps = new Properties();
            consumerProps.put("bootstrap.servers", env.getRequiredProperty("kafka.brokers"));
            consumerProps.put("group.id", env.getRequiredProperty("kafka.consumer.group.id"));
            consumerProps.put("enable.auto.commit", "true");
            consumerProps.put("auto.commit.interval.ms", "1000");
            consumerProps.put("session.timeout.ms", "30000");
            consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            int noOfStreams = Integer.parseInt(env.getRequiredProperty("kafka.num.streams"));
            List<String> topicList = Arrays.asList(env.getRequiredProperty("kafka.topics").split(","));

            ExecutorService executor = Executors.newFixedThreadPool(topicList.size()*noOfStreams);

            for(String topic : topicList) {
                for(int i=0; i < noOfStreams; i++) {
                    kafkaConsumer = new KafkaConsumer<String, String>(consumerProps);
                    kafkaConsumer.subscribe(Arrays.asList(topic));
                    KafkaConsumerThread consumerThread = new KafkaConsumerThread(kafkaConsumer,topic, consumerServiceFactory);
                    executor.submit(consumerThread);
                }
            }
        }catch (Throwable th){
            th.printStackTrace();
        } finally {
            if(kafkaConsumer != null){
                kafkaConsumer.close();
            }
        }
    }
}
