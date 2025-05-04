package com.travelplanner.adapter.out.api.google;

import com.travelplanner.adapter.out.api.google.dto.GoogleAddressComponentDTO;
import com.travelplanner.adapter.out.api.google.dto.GooglePlaceDTO;
import com.travelplanner.adapter.out.api.google.dto.GooglePlaceResponseDTO;
import com.travelplanner.core.model.PlaceModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class GoogleMapper {

    public List<PlaceModel> fromGooglePlaceResponse(GooglePlaceResponseDTO response) {
        List<PlaceModel> places = new ArrayList<>();

        if (response.getPlaces() == null) return places;

        for (GooglePlaceDTO googlePlace : response.getPlaces()) {
            PlaceModel place = new PlaceModel();
            place.setId(googlePlace.getId());
            place.setName(googlePlace.getDisplayName() != null ? googlePlace.getDisplayName().getText() : "");
            place.setLatitude(googlePlace.getLocation() != null ? googlePlace.getLocation().getLatitude() : 0);
            place.setLongitude(googlePlace.getLocation() != null ? googlePlace.getLocation().getLongitude() : 0);
            place.setRating(googlePlace.getRating());
            place.setSource("google");

            // Address parsing
            if (googlePlace.getAddressComponents() != null) {
                for (GoogleAddressComponentDTO component : googlePlace.getAddressComponents()) {
                    if (component.getTypes().contains("administrative_area_level_2")) {
                        place.setCity(component.getLongText());
                    } else if (component.getTypes().contains("country")) {
                        place.setCountry(component.getLongText());
                    }
                }
            }

            places.add(place);
        }

        return places;
    }
}
