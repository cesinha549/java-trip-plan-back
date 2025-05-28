package com.travelplanner.core.trip.domain.model;

import com.travelplanner.feature.place.domain.model.PlaceModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TripModel {
    private String id;
    private String name;
    private DestinationModel destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private List<TripPlaceModel> tripPlaces = new ArrayList<>();
    private List<PlaceModel> suggestedPlaces;
}
