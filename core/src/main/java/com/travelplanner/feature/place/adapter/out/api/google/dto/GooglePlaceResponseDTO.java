package com.travelplanner.feature.place.adapter.out.api.google.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

    @Data
    @Builder
    public class GooglePlaceResponseDTO {
        private List<GooglePlaceDTO> places;
    }
