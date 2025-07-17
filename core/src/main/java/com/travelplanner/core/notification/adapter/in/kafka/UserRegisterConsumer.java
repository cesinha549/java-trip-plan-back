package com.travelplanner.core.notification.adapter.in.kafka;

import com.travelplanner.core.notification.domain.model.notification.NotificationChannel;
import com.travelplanner.core.notification.domain.model.notification.NotificationModel;
import com.travelplanner.core.notification.domain.port.in.NotificationUseCase;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.shared.kafka.KafkaEventIngester;
import com.travelplanner.shared.kafka.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegisterConsumer implements ApplicationRunner {

    private final NotificationUseCase notificationUseCase;

    public UserRegisterConsumer(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    public void parse(ConsumerRecord<String, Message<UserModel>> record) {

        var message = record.value();
        UserModel user = message.getPayload();

        if (user == null) {
            log.warn("Received a null user model in the consumer record. Skipping.");
            return;
        }

        log.info("Received registration event for user: {}", user.email());

        // 1. Create the generic NotificationModel from the Kafka event
        var welcomeNotification = new NotificationModel(
                user.email(),
                "Welcome to Trip Planner!",
                "Hi " + user.name() + ",\n\nWelcome aboard! We're excited to have you.",
                NotificationChannel.EMAIL // 2. Specify the desired channel
        );

        // 3. Pass the generic model to the use case
        notificationUseCase.sendAll(welcomeNotification);

        log.info("Successfully processed welcome notification for user {}", user.email());
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        var  kafkaEventIngester = new KafkaEventIngester("user-topic",
                this::parse,
                "notification-consumer");

        new Thread(kafkaEventIngester::ingestEvent).start();
    }
}
