package com.travelplanner.core.notification.domain.port.in;

import com.travelplanner.core.notification.domain.model.email.EmailModel;

public interface NotificationUseCase {

    public void sendEmail(EmailModel email);
}
