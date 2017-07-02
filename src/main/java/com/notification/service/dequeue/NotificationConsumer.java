package com.notification.service.dequeue;

import com.notification.service.dto.NotificationDTO;
import com.notification.service.utils.NotificationSender;
import com.notification.service.utils.SendMailApi;
import com.notification.service.utils.SendSMSApi;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Aakash on 19/06/17.
 */
public class NotificationConsumer implements ConsumerService{

    private Gson gson = new Gson();
    private ExecutorService executor = Executors.newFixedThreadPool(50);

    @Autowired
    private SendMailApi sendMailApi;

    @Autowired
    private SendSMSApi sendSMSApi;

    public void consumePayload(String payload) {
        if (StringUtils.isBlank(payload)) {
            return;
        }
        try {
            NotificationDTO notificationDTO = gson.fromJson(payload, NotificationDTO.class);
            executor.submit(new NotificationSender(notificationDTO, sendMailApi, sendSMSApi));
        } catch (Throwable th) {
        }
    }
}
