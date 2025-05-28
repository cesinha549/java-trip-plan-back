package com.travelplanner.core.trip.domain.port.in;

import com.travelplanner.core.trip.adapter.in.web.dto.addplace.AddPlaceToTripRequestDTO;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.adapter.in.web.dto.create.TripRequestDTO;

public interface TripUseCase {

    TripModel createTrip(TripRequestDTO tripModel);

    void addPlaceToTrip(AddPlaceToTripRequestDTO dto);
}
