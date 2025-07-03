package com.travelplanner.core.notification.application;

import com.travelplanner.core.notification.domain.model.email.EmailModel;
import com.travelplanner.core.notification.domain.port.in.NotificationUseCase;
import com.travelplanner.core.notification.domain.port.out.NotificationPort;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements NotificationUseCase {

    private final NotificationPort notificationPort;

    public NotificationService(NotificationPort notificationPort) {
        this.notificationPort = notificationPort;
    }

    @Override
    public void sendEmail(EmailModel email) {

    }

    // Implement methods here
}
