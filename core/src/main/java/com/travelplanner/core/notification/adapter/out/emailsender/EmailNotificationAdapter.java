package com.travelplanner.core.notification.adapter.out.emailsender;

import com.travelplanner.core.notification.domain.model.notification.NotificationChannel;
import com.travelplanner.core.notification.domain.model.notification.NotificationModel;
import com.travelplanner.core.notification.domain.port.out.NotificationPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Order(2)
@Slf4j
public class EmailNotificationAdapter implements NotificationPort {

    // The JavaMailSender now lives here, in the adapter, completely
    // isolated from the core domain logic.
    private final JavaMailSender mailSender;

    @Override
    public boolean send(NotificationModel notificationModel) {

        // 1. Check if this adapter should handle this notification
        if (notificationModel.channel() != NotificationChannel.EMAIL) {
            // Not an email, so this adapter does nothing.
            return false;
        }

        log.info("EmailAdapter is handling notification for: {}", notificationModel.recipient());

        try {
            // 2. Translate the generic model to a SimpleMailMessage
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@tripplanner.com");
            message.setTo(notificationModel.recipient());
            message.setSubject(notificationModel.subject());
            message.setText(notificationModel.body());

            // 3. Send the email
            mailSender.send(message);
            log.info("Email sent successfully to {}", notificationModel.recipient());

        } catch (Exception e) {
            log.error("Error sending email to {}: {}", notificationModel.recipient(), e.getMessage());
        }
        return false;
    }
}
