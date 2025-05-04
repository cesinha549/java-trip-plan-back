package com.travelplanner.core.port.out;

import lombok.Data;

import java.util.List;

@Data
public class GoogleAddressComponentDTO {
    private String longText;
    private List<String> types;
}
