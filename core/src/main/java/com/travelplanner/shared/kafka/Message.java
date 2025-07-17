package com.travelplanner.shared.kafka;


public class Message<T> {

    private final CorrelationId id;
    private final T payload;

    public T getPayload() {
        return payload;
    }

    public Message(CorrelationId id, T payload) {
      this.id = id;
      this.payload = payload;
  }

    public CorrelationId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", payload=" + payload +
                '}';
    }
}
