package com.travelplanner.core.trip.adapter.out.db;

import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.model.TripPlaceModel;
import com.travelplanner.core.trip.domain.port.out.TripPersistencePort;
import com.travelplanner.core.trip.domain.port.out.TripQueryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TripPersistenceAdapter implements TripPersistencePort, TripQueryPort {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final TripPlaceRepository tripPlaceRepository;

    public TripPersistenceAdapter(TripRepository tripRepository, TripMapper tripMapper, TripPlaceRepository tripPlaceRepository) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.tripPlaceRepository = tripPlaceRepository;
    }

    @Override
    public TripModel save(TripModel tripModel) {
        TripEntity entity = tripMapper.toEntity(tripModel);
        TripEntity saved = tripRepository.save(entity);
        return tripMapper.toModel(saved);
    }

    @Override
    public Optional<TripModel> findById(String id) {
        return tripRepository.findById(id).map(tripMapper::toModel);
    }

    @Override
    public void addPlaceToTrip(TripPlaceModel tripPlaceModel) {
    TripEntity trip = tripMapper.toEntity(tripPlaceModel.getTrip());
    TripPlaceEntity tripPlaceEntity = tripMapper.toTripPlace(trip,tripPlaceModel.getPlaceId(),tripPlaceModel.getOrderInTrip(),tripPlaceModel.getVisitDate(),tripPlaceModel.getNotes());
    tripPlaceRepository.save(tripPlaceEntity);
    }
}
