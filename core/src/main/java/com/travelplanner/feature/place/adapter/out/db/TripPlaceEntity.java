package com.travelplanner.feature.place.adapter.out.db;

import com.travelplanner.core.trip.adapter.out.db.TripEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name = "trip_place")
@Builder
public class TripPlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private PlaceEntity place;

    // Optional metadata
    private Integer orderInTrip;
    private LocalDate visitDate;
    private String notes;
}
