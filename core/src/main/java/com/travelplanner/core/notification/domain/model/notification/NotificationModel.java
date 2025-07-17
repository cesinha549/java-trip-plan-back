package com.travelplanner.core.notification.domain.model.notification;

import java.util.Map;

public record NotificationModel(String recipient,      // The target address (e.g., email, phone number)
                                String subject,        // The title or subject line
                                String body,           // The main content of the message
                                NotificationChannel channel, // The desired delivery channel
                                Map<String, Object> metadata // Optional data for template engines, etc.) {
){
    // A simple constructor for notifications without metadata
    public NotificationModel(String recipient, String subject, String body, NotificationChannel channel) {
        this(recipient, subject, body, channel, Map.of());
    }
}
