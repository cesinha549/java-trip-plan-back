package com.travelplanner.core.trip.adapter.in.web.dto.create;

import com.travelplanner.core.trip.domain.model.DestinationModel;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.feature.place.adapter.in.web.dto.PlaceResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TripResponseDTO {

    private String id;
    private String name;
    private String city;
    private String state;
    private String country;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private List<PlaceResponseDTO> suggestedPlaces;

    public static TripResponseDTO fromDomain(TripModel model) {
        DestinationModel destination = model.getDestination();

        return TripResponseDTO.builder()
                .id(model.getId())
                .name(model.getName())
                .city(destination != null ? destination.getCity() : null)
                .state(destination != null ? destination.getState() : null)
                .country(destination != null ? destination.getCountry() : null)
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .budget(model.getBudget())
                .suggestedPlaces(model.getSuggestedPlaces() != null
                        ? model.getSuggestedPlaces().stream().map(PlaceResponseDTO::fromDomain).collect(Collectors.toList())
                        : null)
                .build();
    }

}