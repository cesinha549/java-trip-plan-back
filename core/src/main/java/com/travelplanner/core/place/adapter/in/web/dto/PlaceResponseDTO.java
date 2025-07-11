package com.travelplanner.core.place.adapter.in.web.dto;

import com.travelplanner.core.place.domain.model.PlaceModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class PlaceResponseDTO {
    private String id;
    private String name;
    private String description;
    private String category;
    private double latitude;
    private double longitude;
    private String city;
    private String country;
    private double rating;     // Optional: if integrated with Yelp/Google
    private String source;     // Optional: "internal", "yelp", "google", etc.

    public static PlaceResponseDTO fromDomain(PlaceModel place) {
        return PlaceResponseDTO.builder()
                .id(place.getId())
                .name(place.getName())
                .description(place.getDescription())
                .category(place.getCategory())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .city(place.getCity())
                .country(place.getCountry())
                .rating(place.getRating())
                .source(place.getSource())
                .build();
    }

    public static List<PlaceResponseDTO> fromDomainList(List<PlaceModel> places) {
        return places.stream()
                .map(PlaceResponseDTO::fromDomain)
                .collect(Collectors.toList());
    }
}