package com.travelplanner.core.trip.domain.port.in;

import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.adapter.in.web.dto.TripRequestDTO;

public interface TripUseCase {

    TripModel createTrip(TripRequestDTO tripModel);
}
