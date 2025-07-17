package com.travelplanner.core.notification.adapter.out.kafka;

import com.travelplanner.core.notification.domain.model.notification.NotificationModel;
import com.travelplanner.core.notification.domain.port.out.NotificationPort;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class NotificationProducer implements NotificationPort {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public boolean send(NotificationModel notification) {

        return false;
    }

//    @Override
//    public void send(EmailModel message) {
//
//    }
}
