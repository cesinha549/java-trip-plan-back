package com.travelplanner.shared.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaEventDispatcher {

    private final Properties properties;
    private final String topic;

    public KafkaEventDispatcher(Properties properties,  String topic) {
        this.properties = properties;
        this.topic = topic;
    }

    public <T> void sendEvent(String key, T value) {

        try (var producer = new KafkaProducer<String, T>(this.properties)) {
            var record = new ProducerRecord<>(topic, key, value);
            try {
                producer.send(record, (data, ex) -> {
                    if (ex != null) {
                        ex.printStackTrace();
                        return;
                    }
                    System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
                }).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }


    }


}
