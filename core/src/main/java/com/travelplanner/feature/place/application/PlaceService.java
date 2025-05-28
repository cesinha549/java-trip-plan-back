package com.travelplanner.feature.place.application;

import com.travelplanner.feature.place.adapter.out.db.PlaceEntity;
import com.travelplanner.feature.place.domain.model.PlaceModel;
import com.travelplanner.feature.place.domain.port.in.PlaceUseCase;
import com.travelplanner.feature.place.domain.port.out.PlaceSearchPort;
import com.travelplanner.feature.place.domain.port.out.db.PlacePersistencePort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService implements PlaceUseCase {

    private final PlaceSearchPort placeSearchPort;
    private final PlacePersistencePort placePersistencePort;

    public PlaceService(PlaceSearchPort placeSearchPort, PlacePersistencePort placePersistencePort) {
        this.placeSearchPort = placeSearchPort;
        this.placePersistencePort = placePersistencePort;
    }

    @Override
    public List<PlaceModel> getPlaces(Double lat, Double lng, Integer radius, String city, String state, String country, String category) {
        List<PlaceModel> places = placeSearchPort.searchPlaces(lat, lng, radius, city, state, country, category);
        return placePersistencePort.save(places);
    }

    @Override
    public PlaceEntity getPlace(String id) {
        return placePersistencePort.findByIdEntity(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Place not found with id: " + id));
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
