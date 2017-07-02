package com.notification.service.constants;

/**
 * Created by Aakash on 19/06/17.
 */
public enum ClientNotificationPreferences {
    REDIS("redis"),KAFKA("kafka");

    private String notificationPreference;

    ClientNotificationPreferences(String notificationPreference) {
        this.notificationPreference = notificationPreference;
    }

    public String getNotificationPreference() {
        return notificationPreference;
    }

    public static ClientNotificationPreferences fromString(String notificationPreference) {

        for(ClientNotificationPreferences value : values()) {
            if(value.getNotificationPreference().equals(notificationPreference)) {
                return value;
            }
        }
        return null;
    }
}
