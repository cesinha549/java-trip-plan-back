package com.travelplanner.core.trip.application;

import com.travelplanner.core.trip.adapter.out.db.TripMapper;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.port.in.TripUseCase;
import com.travelplanner.core.trip.adapter.in.web.dto.TripRequestDTO;
import com.travelplanner.core.trip.domain.port.out.TripPersistencePort;
import com.travelplanner.feature.place.domain.port.in.PlaceUseCase;
import org.springframework.stereotype.Service;


@Service
public class CreateTripService implements TripUseCase {

    private final TripPersistencePort tripPersistencePort;

    private final PlaceUseCase placeUseCase;

    public CreateTripService(TripPersistencePort tripPersistencePort, PlaceUseCase placeUseCase) {
        this.tripPersistencePort = tripPersistencePort;
        this.placeUseCase = placeUseCase;
    }

    @Override
    public TripModel createTrip(TripRequestDTO tripRequest) {

        // 1. Get suggested places
        var suggestedPlaces = placeUseCase.getPlaces(
                0.0, 0.0, 0,
                tripRequest.getCity(),
                tripRequest.getState(),
                tripRequest.getCountry(),
                "tourist_attraction"
        );

        // 2. Build TripModel
        TripModel trip = TripMapper.fromRequestDTO(tripRequest);
        trip.setSuggestedPlaces(suggestedPlaces);

        // 3. Save trip (with mapped places)
        var savedTripEntity = tripPersistencePort.save(trip);
        savedTripEntity.setSuggestedPlaces(suggestedPlaces);

        return savedTripEntity;
    }
}
