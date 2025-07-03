package com.travelplanner.feature.place.adapter.out.db;


import com.travelplanner.core.trip.adapter.out.db.TripEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "places")
@Data
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String category;
    private double latitude;
    private double longitude;
    private String city;
    private String country;
    private double rating;     // Optional: if integrated with Yelp/Google
    private String source;     // Optional: "internal", "yelp", "google", etc.
}
