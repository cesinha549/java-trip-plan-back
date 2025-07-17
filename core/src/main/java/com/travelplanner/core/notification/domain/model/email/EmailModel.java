package com.travelplanner.core.notification.domain.model.email;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailModel {

    private String header;
    private String body;
    private String destinationEmail;
}
