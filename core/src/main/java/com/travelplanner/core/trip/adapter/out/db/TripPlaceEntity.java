package com.travelplanner.core.trip.adapter.out.db;

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

    @Column(name = "place_id")
    private String placeId;

    // Optional metadata
    private Integer orderInTrip;
    private LocalDate visitDate;
    private String notes;
}
