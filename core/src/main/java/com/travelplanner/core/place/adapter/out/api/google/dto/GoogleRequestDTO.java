package com.travelplanner.core.place.adapter.out.api.google.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleRequestDTO {

    private String textQuery;

}
