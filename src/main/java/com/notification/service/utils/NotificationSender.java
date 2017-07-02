package com.notification.service.utils;

import com.notification.service.dto.NotificationDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aakash on 19/06/17.
 */
public class NotificationSender implements Runnable{

    private NotificationDTO notificationDTO;
    private SendMailApi sendMailApi;
    private SendSMSApi sendSMSApi;

    public NotificationSender(NotificationDTO notificationDTO, SendMailApi sendMailApi, SendSMSApi sendSMSApi) {
        this.notificationDTO = notificationDTO;
        this.sendMailApi = sendMailApi;
        this.sendSMSApi = sendSMSApi;
    }

    public void run() {
        if(notificationDTO == null){
            return;
        }

        if(StringUtils.isNotBlank(notificationDTO.getEmail()) && StringUtils.isNotBlank(notificationDTO.getEmailMessage())){
            List<String> emailList = new ArrayList();
            emailList.add(notificationDTO.getEmail());
            /*send Mail Call */
        }

        if(StringUtils.isNotBlank(notificationDTO.getMobile()) && StringUtils.isNotBlank(notificationDTO.getSmsMessage())){
            /*send SMS */
        }
    }
}
