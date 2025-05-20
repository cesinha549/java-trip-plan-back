package com.travelplanner.core.trip.domain.port.out;

import com.travelplanner.core.trip.domain.model.TripModel;

import java.util.Optional;

public interface TripPersistencePort {

    public TripModel save(TripModel tripModel);

    public Optional<TripModel> findById(String id);
}
