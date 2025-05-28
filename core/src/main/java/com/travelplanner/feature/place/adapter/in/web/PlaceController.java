package com.travelplanner.feature.place.adapter.in.web;

import com.travelplanner.feature.place.adapter.in.web.dto.PlaceResponseDTO;
import com.travelplanner.feature.place.domain.port.in.PlaceUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final PlaceUseCase placeUseCase;

    public PlaceController(PlaceUseCase placeUseCase) {
        this.placeUseCase = placeUseCase;
    }

    @GetMapping("/{id}")
    public PlaceResponseDTO getPlaceById(@PathVariable String id) {

//      return PlaceResponseDTO.fromDomain(placeUseCase.getPlace(id));
        return null;
    }

    @GetMapping("")
    public List<PlaceResponseDTO> getPlaces(@RequestParam(required = false) Double lat,
                                            @RequestParam(required = false) Double lng,
                                            @RequestParam(required = false) Integer radius,
                                            @RequestParam(required = false) String city,
                                            @RequestParam(required = false) String state,
                                            @RequestParam(required = false) String country,
                                            @RequestParam(required = false) String category) {


       return PlaceResponseDTO.fromDomainList(placeUseCase.getPlaces(lat,lng,radius,city,state,country,category));
    }
}
