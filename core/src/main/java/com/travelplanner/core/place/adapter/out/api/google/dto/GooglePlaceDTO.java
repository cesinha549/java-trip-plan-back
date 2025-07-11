package com.travelplanner.core.place.adapter.out.api.google.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GooglePlaceDTO {
    private String id;
    private GoogleDisplayNameDTO displayName;
    private GoogleLocationDTO location;
    private Double rating;
    private String source = "google";
    private List<GoogleAddressComponentDTO> addressComponents;
}
