package com.travelplanner.core.place.domain.port.in;

import com.travelplanner.core.place.domain.model.PlaceModel;
import java.util.List;

public interface PlaceUseCase {

    List<PlaceModel> getPlaces( Double lat,
                                Double lng,
                                Integer radius,
                                String city,
                                String state,
                                String country,
                                String category
                                );
   PlaceModel getPlace(String id);
    Integer addPlace(Double lat,
                     Double lng,
                     String city,
                     String state,
                     String country,
                     String category,
                     String details);
    void deletePlace(Integer id);
    void reviewPlace(Integer id, Integer grade);
    void addPhotoPlace(Integer id,String base64);
    void addCommentPlace(Integer id,String comment);


    List<PlaceModel> suggestPlacesForTrip(String tripId);
}
