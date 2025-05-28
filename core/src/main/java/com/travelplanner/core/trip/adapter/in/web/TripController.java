package com.travelplanner.core.trip.adapter.in.web;


import com.travelplanner.core.trip.adapter.in.web.dto.addplace.AddPlaceToTripRequestDTO;
import com.travelplanner.core.trip.adapter.in.web.dto.create.TripResponseDTO;
import com.travelplanner.core.trip.domain.port.in.TripUseCase;
import com.travelplanner.core.trip.adapter.in.web.dto.create.TripRequestDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripUseCase tripUseCase;

    public TripController(TripUseCase tripUseCase) {
        this.tripUseCase = tripUseCase;
    }

    @PostMapping("/start")
    public ResponseEntity<TripResponseDTO> startTrip(@RequestBody TripRequestDTO request) {
        return ResponseEntity.ok(TripResponseDTO.fromDomain(tripUseCase.createTrip(request)));
    }

    @PostMapping("/add_place")
    public ResponseEntity<Void> addPlaceToTrip(@RequestBody AddPlaceToTripRequestDTO request) {
        tripUseCase.addPlaceToTrip(request);
        return ResponseEntity.noContent().build();
    }
}
