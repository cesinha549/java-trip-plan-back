package com.travelplanner.core.notification.adapter.in.kafka;

import com.travelplanner.core.notification.domain.port.in.NotificationUseCase;
import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.shared.kafka.KafkaEventIngester;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer implements ApplicationRunner {

    private final NotificationUseCase notificationUseCase;

    public NotificationConsumer(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    public static void parse(ConsumerRecord<String, UserModel> record) {
        log.info("Processing transaction {}",record);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var  kafkaEventIngester = new KafkaEventIngester("user-topic",NotificationConsumer::parse,"notification-consumer", UserModel.class);
        new Thread(kafkaEventIngester::ingestEvent).start();
    }
}
