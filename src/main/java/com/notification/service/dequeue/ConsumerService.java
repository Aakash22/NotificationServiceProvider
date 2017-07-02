package com.notification.service.dequeue;

/**
 * Created by Aakash on 19/06/17.
 */
public interface ConsumerService {

    void consumePayload(String payload);
}
