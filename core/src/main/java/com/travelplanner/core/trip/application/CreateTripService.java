package com.travelplanner.core.trip.application;

import com.travelplanner.core.trip.adapter.in.web.dto.addplace.AddPlaceToTripRequestDTO;
import com.travelplanner.core.trip.adapter.out.db.TripMapper;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.model.TripPlaceModel;
import com.travelplanner.core.trip.domain.port.in.TripUseCase;
import com.travelplanner.core.trip.adapter.in.web.dto.create.TripRequestDTO;
import com.travelplanner.core.trip.domain.port.out.TripPersistencePort;
import com.travelplanner.feature.place.domain.port.in.PlaceUseCase;
import jakarta.persistence.EntityNotFoundException;
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

        // 2. Build TripModel to save on the DB without suggested places
        TripModel trip = TripMapper.fromRequestDTO(tripRequest);

        // 3. Save trip (with mapped places)
        var savedTripEntity = tripPersistencePort.save(trip);

        //Add suggested places to return for the client, could be improved
        savedTripEntity.setSuggestedPlaces(suggestedPlaces);

        return savedTripEntity;
    }

    @Override
    public void addPlaceToTrip(AddPlaceToTripRequestDTO addPlaceToTripRequestDTO) {
        TripModel trip = tripPersistencePort.findById(addPlaceToTripRequestDTO.getTripId())
                .orElseThrow(() -> new EntityNotFoundException("Trip not found"));

        TripPlaceModel tripPlace = TripPlaceModel.builder()
                .trip(trip)
                .placeId(addPlaceToTripRequestDTO.getPlaceId())
                .orderInTrip(addPlaceToTripRequestDTO.getOrder())
                .visitDate(addPlaceToTripRequestDTO.getVisitDate())
                .notes(addPlaceToTripRequestDTO.getNotes())
                .build();

        tripPersistencePort.addPlaceToTrip(tripPlace);
    }
}
