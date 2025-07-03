package com.travelplanner.core.trip.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TripPlaceModel {

        private String id;

        private TripModel trip;

        private String placeId;

        // Optional metadata
        private Integer orderInTrip;
        private LocalDate visitDate;
        private String notes;
    }
