package com.travelplanner.core.trip.adapter.out.db;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Entity
@Table(name = "trips")
@Data
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL) // Saves destination when trip is saved
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private DestinationEntity destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;

    // getters/setters
}