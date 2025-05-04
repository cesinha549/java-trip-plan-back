package com.travelplanner.core.port.out;

import com.travelplanner.core.model.PlaceModel;

import java.util.List;

public interface PlaceSearchPort {

    List<PlaceModel> searchPlaces(Double lat, Double lng, Integer radius, String city, String state, String country, String category);
}
