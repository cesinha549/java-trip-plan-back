package com.travelplanner.feature.place.application;

import com.travelplanner.feature.place.domain.model.PlaceModel;
import com.travelplanner.feature.place.domain.port.in.PlaceUseCase;
import com.travelplanner.feature.place.domain.port.out.PlaceSearchPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService implements PlaceUseCase {

    private final PlaceSearchPort placeSearchPort;

    public PlaceService(PlaceSearchPort placeSearchPort) {
        this.placeSearchPort = placeSearchPort;
    }

    @Override
    public List<PlaceModel> getPlaces(Double lat, Double lng, Integer radius, String city, String state, String country, String category) {
        return this.placeSearchPort.searchPlaces(0.0,0.0,0,city,state,country,category);
    }

    @Override
    public PlaceModel getPlace(Integer id) {
        return null;
    }

    @Override
    public Integer addPlace(Double lat, Double lng, String city, String state, String country, String category, String details) {
        return 0;
    }

    @Override
    public void deletePlace(Integer id) {

    }

    @Override
    public void reviewPlace(Integer id, Integer grade) {

    }

    @Override
    public void addPhotoPlace(Integer id, String base64) {

    }

    @Override
    public void addCommentPlace(Integer id, String comment) {

    }

}
