package com.travelplanner.feature.place.adapter.in.web.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TripRequestDTO {

        private String name;
        private String city;
        private String state;
        private String country;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<String> tags;
    }

