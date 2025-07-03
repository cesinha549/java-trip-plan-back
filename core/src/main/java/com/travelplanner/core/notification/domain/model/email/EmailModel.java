package com.travelplanner.core.notification.domain.model.email;

import lombok.Data;

@Data
public class EmailModel {

    private String header;
    private String body;
    private String destinationEmail;
}
