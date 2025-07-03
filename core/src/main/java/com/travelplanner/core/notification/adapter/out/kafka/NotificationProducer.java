package com.travelplanner.core.notification.adapter.out.kafka;

import com.travelplanner.core.notification.domain.port.out.NotificationPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer implements NotificationPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEvent(String message) {
//        kafkaTemplate.send("notification-events", message);
    }
}
