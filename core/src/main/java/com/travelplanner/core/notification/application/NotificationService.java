package com.travelplanner.core.notification.application;

import com.travelplanner.core.notification.domain.model.notification.NotificationModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.travelplanner.core.notification.domain.model.email.EmailModel;
import com.travelplanner.core.notification.domain.port.in.NotificationUseCase;
import com.travelplanner.core.notification.domain.port.out.NotificationPort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class NotificationService implements NotificationUseCase {

    private final List<NotificationPort> notificationPorts;

    public NotificationService(List<NotificationPort> notificationPorts) {
        this.notificationPorts = notificationPorts;
    }

    @Override
    public boolean sendByPriority(NotificationModel notificationModel) {
        log.info("Executing notification chain for recipient: {}", notificationModel.recipient());

        for (NotificationPort port : notificationPorts) {
            if (port.send(notificationModel)) {
                log.info("Notification handled by: {}. Stopping chain.", port.getClass().getSimpleName());
                return true; // Success, stop the chain.
            }
        }

        log.warn("Notification for {} was not handled by any port in the chain.", notificationModel.recipient());
        return false; // No handler was successful.
    }

    @Override
    public void sendAll(NotificationModel notification) {
        log.info("Broadcasting notification to all ports for recipient: {}", notification.recipient());

        // Use forEach to send to all handlers.
        notificationPorts.forEach(port -> port.send(notification));
    }

    // Implement methods here
}
