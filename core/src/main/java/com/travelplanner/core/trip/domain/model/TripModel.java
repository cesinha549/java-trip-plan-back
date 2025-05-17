package com.travelplanner.core.trip.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TripModel {
    private String id;
    private String name;
    private DestinationModel destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
}
