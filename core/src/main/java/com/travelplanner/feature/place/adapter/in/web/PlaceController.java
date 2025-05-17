package com.travelplanner.feature.place.adapter.in.web;

import com.travelplanner.core.trip.adapter.out.db.TripMapper;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.feature.place.adapter.in.web.dto.PlaceResponseDTO;
import com.travelplanner.feature.place.adapter.in.web.dto.TripRequestDTO;
import com.travelplanner.feature.place.domain.model.PlaceModel;
import com.travelplanner.feature.place.domain.port.in.PlaceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceUseCase placeUseCase;

    private final TripMapper tripMapper;

    public PlaceController(PlaceUseCase placeUseCase, TripMapper tripMapper) {
        this.placeUseCase = placeUseCase;
        this.tripMapper = tripMapper;
    }

    @GetMapping("/{id}")
    public PlaceResponseDTO getPlaceById(@PathVariable String id) {

     return PlaceResponseDTO.fromDomain(placeUseCase.getPlace(Integer.getInteger(id)));
    }

    @GetMapping("")
    public List<PlaceResponseDTO> getPlaces(@RequestParam(required = false) Double lat,
                                            @RequestParam(required = false) Double lng,
                                            @RequestParam(required = false) Integer radius,
                                            @RequestParam(required = false) String city,
                                            @RequestParam(required = false) String state,
                                            @RequestParam(required = false) String country,
                                            @RequestParam(required = false) String category) {


       return PlaceResponseDTO.fromDomainList(placeUseCase.getPlaces(0.0,0.0,0,city,state,country,category));
    }

    @PostMapping("/trip/start")
    public ResponseEntity<List<PlaceResponseDTO>> startTrip(@RequestBody TripRequestDTO request) {
        TripModel tripModel = TripMapper.fromRequestDTO(request);
        List<PlaceModel> suggestions = placeUseCase.startTripAndSuggestPlaces(tripModel);
        return ResponseEntity.ok(
                suggestions.stream().map(PlaceResponseDTO::fromDomain).toList()
        );
    }


}
