package com.travelplanner.core.notification.domain.port.in;

import com.travelplanner.core.notification.domain.model.email.EmailModel;
import com.travelplanner.core.notification.domain.model.notification.NotificationModel;

public interface NotificationUseCase {


    /**
     * Sends a notification using a priority-based chain of responsibility.
     * It will try each available channel in order until one succeeds.
     *
     * @param notificationModel The notification to send.
     * @return true if the notification was sent successfully by at least one channel, false otherwise.
     */
    boolean sendByPriority(NotificationModel notificationModel);

    /**
     * Sends a notification to all applicable channels simultaneously.
     *
     * @param notificationModel The notification to send.
     */
    void sendAll(NotificationModel notificationModel);
}
