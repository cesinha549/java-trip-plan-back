package com.travelplanner.core.notification.domain.port.out;

public interface NotificationPort {
    // Define external port methods here

    public void sendEvent(String message);
}
