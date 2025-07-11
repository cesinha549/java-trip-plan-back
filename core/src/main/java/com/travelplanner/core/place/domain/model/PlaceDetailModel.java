package com.travelplanner.core.place.domain.model;

import lombok.Data;

@Data
public class PlaceDetailModel {

    private Integer minimunAge = 0;
    private Double startHour = 0.0;
    private Double endHour = 0.0;
    private boolean hasSmokingArea = false;
    private boolean hasEvents = false;// like concerts, game watch, etc, ask chat gpt for more inside on that


}
