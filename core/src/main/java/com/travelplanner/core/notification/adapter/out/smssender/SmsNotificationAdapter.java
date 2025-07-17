package com.travelplanner.core.notification.adapter.out.smssender;

import com.travelplanner.core.notification.domain.model.notification.NotificationModel;
import com.travelplanner.core.notification.domain.port.out.NotificationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Order(1)
public class SmsNotificationAdapter implements NotificationPort {


    @Override
    public boolean send(NotificationModel notificationModel) {
        // 1. Check if this adapter should handle this notification.
//        if (notificationModel.channel() != NotificationChannel.SMS) {
//            // Not an SMS, so this adapter does nothing.
//            return false;
//        }

        log.info("SmsAdapter is handling notification for: {}", notificationModel.recipient());

        // 2. In a real-world scenario, you would translate the model
        //    and use an external service (like Twilio, Vonage, etc.) to send the SMS.
        //    For this example, we will just log it to the console.
        log.info("--- SIMULATING SMS SEND ---");
        log.info("To: {}", notificationModel.recipient());
        log.info("Message: {}", notificationModel.body());
        log.info("--- END SMS SIMULATION ---");

        // No try/catch is needed for this simulation, but in a real implementation
        // you would handle potential exceptions from the SMS gateway client.
        return false;
    }
}
