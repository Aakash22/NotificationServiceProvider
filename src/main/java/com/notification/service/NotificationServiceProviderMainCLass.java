package com.notification.service;

import com.google.gson.Gson;
import com.notification.service.constants.ClientNotificationPreferences;
import com.notification.service.dto.NotificationDTO;
import com.notification.service.enqueue.EnqueueDataService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static com.notification.service.constants.QueueTopic.SMS_EMAIL_NOTIFICATION;

/**
 * Created by Aakash
 *
 */
public class NotificationServiceProviderMainCLass
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.aakash");
        EnqueueDataService enqueueDataService = context.getBean(EnqueueDataService.class);

        Gson gson = new Gson();

        /* Send Email Notification Data to Redis*/
        NotificationDTO emailNotificationDTO = new NotificationDTO();
        emailNotificationDTO.setEmail("aakashnitdelhi@gmail.com");
        emailNotificationDTO.setEmailMessage("Email Testing");
        emailNotificationDTO.setNotificationEmailSubject("Testing Email Subject");
        String emailPayload = gson.toJson(emailNotificationDTO);
        enqueueDataService.queuingDataService(ClientNotificationPreferences.REDIS,SMS_EMAIL_NOTIFICATION,emailPayload);

        /* Send SMS Notification Data to Kafka*/
        NotificationDTO smsNotificationDTO = new NotificationDTO();
        smsNotificationDTO.setMobile("+918826639105");
        smsNotificationDTO.setSmsMessage("SMS Testing ");
        String smsPayload = gson.toJson(smsNotificationDTO);
        enqueueDataService.queuingDataService(ClientNotificationPreferences.KAFKA,SMS_EMAIL_NOTIFICATION,smsPayload);


    }
}
