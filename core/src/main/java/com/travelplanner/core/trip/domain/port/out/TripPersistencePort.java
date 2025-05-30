package com.travelplanner.core.trip.domain.port.out;

import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.domain.model.TripPlaceModel;

import java.util.Optional;

public interface TripPersistencePort {

    public TripModel save(TripModel tripModel);

    public void addPlaceToTrip(TripPlaceModel tripPlaceModel);
}
