package com.planner.application.notification;

public interface NotificationService {
    void sendNotification(String recipient, String subject, String content);
}
