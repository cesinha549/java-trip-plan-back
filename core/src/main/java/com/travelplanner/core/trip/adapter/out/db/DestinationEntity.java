package com.travelplanner.core.trip.adapter.out.db;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "destinations")
@Data
public class DestinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String city;
    private String country;
    private String state;

}
