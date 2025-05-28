package com.travelplanner.core.trip.adapter.in.web.dto.addplace;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddPlaceToTripRequestDTO {
    private String tripId;
    private String placeId;
    private Integer order; // optional
    private LocalDate visitDate; // optional
    private String notes; // optional
}
