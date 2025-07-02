package com.travelplanner.core.user.adapter.out.kafka;


import com.travelplanner.core.user.domain.port.out.UserEventPort;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Component
public class UserRegisterEventProducer implements UserEventPort {


    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    public void sendRegisterEvent() {

        try (var producer = new KafkaProducer<String, String>(properties())) {
            var value = "132123,67523,7894589745";
            var record = new ProducerRecord<>("user-topic", value, value);
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
