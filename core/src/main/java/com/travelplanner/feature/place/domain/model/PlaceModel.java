package com.travelplanner.feature.place.domain.model;

import lombok.Data;

@Data
public class PlaceModel {

    private String id = "";
    private String name= "";
    private String description= "";
    private String category= "";
    private double latitude = 0;
    private double longitude= 0;
    private String city= "";
    private String country= "";
    private double rating= 0;     // Optional: if integrated with Yelp/Google
    private String source= "";     // Optional: "internal", "yelp", "google", etc.

}
