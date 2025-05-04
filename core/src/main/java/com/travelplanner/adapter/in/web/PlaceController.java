package com.travelplanner.adapter.in.web;

import com.travelplanner.adapter.in.web.dto.PlaceDTO;
import com.travelplanner.core.port.in.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    private final Place place;

    public PlaceController(Place place) {
        this.place = place;
    }

    @GetMapping("/{id}")
    public PlaceDTO getPlaceById(@PathVariable String id) {

     return PlaceDTO.fromDomain(place.getPlace(Integer.getInteger(id)));
    }

    @GetMapping("")
    public List<PlaceDTO> getPlaces(@RequestParam(required = false) Double lat,
                                    @RequestParam(required = false) Double lng,
                                    @RequestParam(required = false) Integer radius,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String state,
                                    @RequestParam(required = false) String country,
                                    @RequestParam(required = false) String category) {


       return PlaceDTO.fromDomainList(place.getPlaces(0.0,0.0,0,city,state,country,category));
    }
}
