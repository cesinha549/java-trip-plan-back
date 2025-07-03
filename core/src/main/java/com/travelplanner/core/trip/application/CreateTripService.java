package com.travelplanner.core.trip.application;

import com.travelplanner.core.trip.adapter.in.web.dto.addplace.AddPlaceToTripRequestDTO;
import com.travelplanner.core.trip.adapter.out.db.TripMapper;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.model.TripPlaceModel;
import com.travelplanner.core.trip.domain.port.in.TripUseCase;
import com.travelplanner.core.trip.adapter.in.web.dto.create.TripRequestDTO;
import com.travelplanner.core.trip.domain.port.out.TripPersistencePort;
import com.travelplanner.core.trip.domain.port.out.TripQueryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CreateTripService implements TripUseCase {

    private final TripPersistencePort tripPersistencePort;

    private final TripQueryPort tripQueryPort;

    public CreateTripService(TripPersistencePort tripPersistencePort, TripQueryPort tripQueryPort) {
        this.tripPersistencePort = tripPersistencePort;
        this.tripQueryPort = tripQueryPort;
    }

    @Override
    public TripModel createTrip(TripRequestDTO tripRequest) {

        // 2. Build TripModel to save on the DB without suggested places
        TripModel trip = TripMapper.fromRequestDTO(tripRequest);

        // 3. Save trip (with mapped places)

        return tripPersistencePort.save(trip);
    }

    @Override
    public void addPlaceToTrip(AddPlaceToTripRequestDTO addPlaceToTripRequestDTO) {
        TripModel trip = tripQueryPort.findById(addPlaceToTripRequestDTO.getTripId())
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
