package com.travelplanner.core.trip.adapter.out.db;

import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.port.out.TripPersistencePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TripPersistenceAdapter implements TripPersistencePort {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    public TripPersistenceAdapter(TripRepository tripRepository, TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
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
}
