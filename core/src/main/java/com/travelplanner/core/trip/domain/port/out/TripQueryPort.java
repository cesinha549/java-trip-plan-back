package com.travelplanner.core.trip.domain.port.out;

import com.travelplanner.core.trip.domain.model.TripModel;

import java.util.Optional;

public interface TripQueryPort {

    public Optional<TripModel> findById(String id);

}
