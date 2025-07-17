package com.travelplanner.shared.kafka;

import com.travelplanner.core.notification.domain.model.email.EmailModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

public class KafkaEventIngester implements Closeable {

    private final KafkaConsumer<String, Message> consumer;
    private final ConsumerFunction parse;
    private final String groupId;

    public KafkaEventIngester(String topic, ConsumerFunction parse, String groupId) {
        this.parse = parse;
        this.groupId = groupId;
        this.consumer = new KafkaConsumer<>(this.properties());
        consumer.subscribe(Collections.singletonList(topic));
    }

    public void ingestEvent() {
        while(true) {
            var records = this.consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
                System.out.println("Encontrei " + records.count() + " registros");
                for (var record : records) {
                    parse.consume(record);
                }
            }
        }
    }

    private Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        return properties;
    }


    @Override
    public void close() throws IOException {
        consumer.close();
    }
}
