package com.travelplanner.adapter.out.api.google.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoogleAddressComponentDTO {
    private String longText;
    private List<String> types;
}
