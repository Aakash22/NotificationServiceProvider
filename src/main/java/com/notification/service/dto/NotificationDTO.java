package com.notification.service.dto;

/**
 * Created by Aakash on 19/06/17.
 */
public class NotificationDTO {
    private String notificationType;
    private String email;
    private String mobile;
    private String emailMessage;
    private String smsMessage;
    private String notificationEmailSubject;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getNotificationEmailSubject() {
        return notificationEmailSubject;
    }

    public void setNotificationEmailSubject(String notificationEmailSubject) {
        this.notificationEmailSubject = notificationEmailSubject;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "notificationType='" + notificationType + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", emailMessage='" + emailMessage + '\'' +
                ", smsMessage='" + smsMessage + '\'' +
                ", notificationEmailSubject='" + notificationEmailSubject + '\'' +
                '}';
    }
}
