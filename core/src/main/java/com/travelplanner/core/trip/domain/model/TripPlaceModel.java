package com.travelplanner.core.trip.domain.model;

import com.travelplanner.core.trip.adapter.out.db.TripEntity;
import com.travelplanner.feature.place.adapter.out.db.PlaceEntity;
import com.travelplanner.feature.place.domain.model.PlaceModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TripPlaceModel {

        private String id;

        private TripModel trip;

        private PlaceEntity place; //TODO - isso não deveria estar aqui, eu acho!

        // Optional metadata
        private Integer orderInTrip;
        private LocalDate visitDate;
        private String notes;
    }
