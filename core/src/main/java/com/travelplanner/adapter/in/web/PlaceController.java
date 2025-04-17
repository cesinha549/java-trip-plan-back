package com.travelplanner.adapter.in.web;

import com.travelplanner.adapter.in.web.dto.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @GetMapping("/{id}")
    public PlaceDTO getPlaceById(@PathVariable String id) {

     return PlaceDTO.builder()
                .id("123")
                .name("Christ the Redeemer")
                .description("Famous statue in Rio")
                .category("landmark")
                .latitude(-22.9519)
                .longitude(-43.2105)
                .city("Rio de Janeiro")
                .country("Brazil")
                .rating(4.9)
                .source("internal")
                .build();
    }

    @GetMapping("")
    public List<PlaceDTO> getPlaces(@RequestParam(required = false) Double lat,
                                    @RequestParam(required = false) Double lng,
                                    @RequestParam(required = false) Integer radius,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String state,
                                    @RequestParam(required = false) String country) {

        List<PlaceDTO> places = new ArrayList<PlaceDTO>();

       PlaceDTO place =   PlaceDTO.builder()
                .id("123")
                .name("Christ the Redeemer")
                .description("Famous statue in Rio")
                .category("landmark")
                .latitude(-22.9519)
                .longitude(-43.2105)
                .city("Rio de Janeiro")
                .country("Brazil")
                .rating(4.9)
                .source("internal")
                .build();
       places.add(place);
       places.add(place);

       return places;
    }
}
