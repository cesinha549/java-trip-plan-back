package com.travelplanner.core.user.adapter.out.kafka;


import com.travelplanner.core.user.domain.model.UserModel;
import com.travelplanner.core.user.domain.port.out.UserEventPort;
import com.travelplanner.shared.kafka.GsonSerializer;
import com.travelplanner.shared.kafka.KafkaEventDispatcher;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class UserRegisterEventProducer implements UserEventPort {


    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG,"all");
        return properties;
    }

    public void sendRegisterEvent(UserModel userModel) {
        KafkaEventDispatcher kafkaEventDispatcher = new KafkaEventDispatcher(properties(),"user-topic");
        kafkaEventDispatcher.sendEvent(userModel.email(),userModel);
    }


}
