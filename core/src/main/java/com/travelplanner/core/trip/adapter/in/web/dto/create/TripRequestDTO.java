package com.travelplanner.core.trip.adapter.in.web.dto.create;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TripRequestDTO {

        private String userId;
        private String name;
        private String city;
        private String state;
        private String country;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<String> tags;
    }

