package com.travelplanner.shared.kafka;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageAdapter implements JsonSerializer<Message>, JsonDeserializer<Message> {
    @Override
    public JsonElement serialize(Message message, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.add("payload",jsonSerializationContext.serialize(message.getPayload()));
        object.add("id", jsonSerializationContext.serialize(message.getId()));
        object.addProperty("type",message.getPayload().getClass().getName());
        return object;
    }

    @Override
    public Message deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        var obj = jsonElement.getAsJsonObject();
        var payloadType = obj.get("type").getAsString();
        var correlationId = context.deserialize(obj.get("id"), CorrelationId.class);
        try {
            var payload = context.deserialize(obj.get("payload"),Class.forName(payloadType));
             return new Message<>((CorrelationId) correlationId,payload);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Class not found for deserialization");
        }
    }
}
