package com.travelplanner.core.trip.domain.port.in;

import com.travelplanner.core.trip.domain.model.TripModel;

public interface CreateTripUseCase {

    TripModel create(TripModel tripModel);
}
