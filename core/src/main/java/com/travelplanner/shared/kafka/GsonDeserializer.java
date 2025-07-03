package com.travelplanner.shared.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class GsonDeserializer<T> implements Deserializer<T> {

    public static final String TYPE_CONFIG = "br.alura.ecommerce.type.config";
    private final Gson gson = new GsonBuilder().create();
    private Class<?> type;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String typeName = String.valueOf(configs.get(TYPE_CONFIG));
        try {
           this.type = Class.forName(typeName);
            System.out.println("🧠 Deserializer configured for type: " + typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Type for deserealization does not exist on classpath",e);
        }
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return gson.fromJson(new String(bytes), (Class<T>) type);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }

//    @Override
//    public T deserialize(String s,byte[] bytes) throws DeserializationException {
//        return gson.fromJson(bytes,type);
//    }




}
