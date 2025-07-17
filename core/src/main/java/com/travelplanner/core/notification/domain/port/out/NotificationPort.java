package com.travelplanner.core.notification.domain.port.out;

import com.travelplanner.core.notification.domain.model.notification.NotificationModel;

public interface NotificationPort {
    // Define external port methods here
    public boolean send(NotificationModel notification);

}
