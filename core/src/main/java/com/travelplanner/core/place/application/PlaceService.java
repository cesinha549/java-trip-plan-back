package com.travelplanner.core.place.application;

import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.port.out.TripQueryPort;
import com.travelplanner.core.place.adapter.out.db.PlaceMapper;
import com.travelplanner.core.place.domain.model.PlaceModel;
import com.travelplanner.core.place.domain.port.in.PlaceUseCase;
import com.travelplanner.core.place.domain.port.out.PlaceSearchPort;
import com.travelplanner.core.place.domain.port.out.db.PlacePersistencePort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService implements PlaceUseCase {

    private final PlaceSearchPort placeSearchPort;
    private final PlacePersistencePort placePersistencePort;
    private final TripQueryPort tripQueryPort; // if this were separated projects thiw would be an httpCall

    public PlaceService(PlaceSearchPort placeSearchPort, PlacePersistencePort placePersistencePort, TripQueryPort tripQueryPort) {
        this.placeSearchPort = placeSearchPort;
        this.placePersistencePort = placePersistencePort;
        this.tripQueryPort = tripQueryPort;
    }

    @Override
    public List<PlaceModel> getPlaces(Double lat, Double lng, Integer radius, String city, String state, String country, String category) {
        List<PlaceModel> places = placeSearchPort.searchPlaces(lat, lng, radius, city, state, country, category);
        return placePersistencePort.save(places);
    }

    @Override
    public PlaceModel getPlace(String id) {

        var placeEntity = placePersistencePort.findByIdEntity(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Place not found with id: " + id));

        return PlaceMapper.toModel(placeEntity);
    }

    @Override
    public List<PlaceModel> suggestPlacesForTrip(String tripId) {
        TripModel trip = tripQueryPort.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        List<PlaceModel> places = placeSearchPort.searchPlaces(
                0.0, 0.0, 0,
                trip.getDestination().getCity(),
                trip.getDestination().getState(),
                trip.getDestination().getCountry(),
                "tourist_attraction"
        );

        return placePersistencePort.save(places);
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
