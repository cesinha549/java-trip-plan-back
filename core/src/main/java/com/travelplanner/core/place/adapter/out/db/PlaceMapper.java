package com.travelplanner.core.place.adapter.out.db;

import com.travelplanner.core.place.domain.model.PlaceModel;

public class PlaceMapper {

    public static PlaceEntity toEntity(PlaceModel model) {
        if (model == null) return null;

        PlaceEntity entity = new PlaceEntity();
//        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setCategory(model.getCategory());
        entity.setLatitude(model.getLatitude());
        entity.setLongitude(model.getLongitude());
        entity.setCity(model.getCity());
        entity.setCountry(model.getCountry());
        entity.setRating(model.getRating());
        entity.setSource(model.getSource());

        return entity;
    }

    public static PlaceModel toModel(PlaceEntity entity) {
        if (entity == null) return null;

        PlaceModel model = new PlaceModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setCategory(entity.getCategory());
        model.setLatitude(entity.getLatitude());
        model.setLongitude(entity.getLongitude());
        model.setCity(entity.getCity());
        model.setCountry(entity.getCountry());
        model.setRating(entity.getRating());
        model.setSource(entity.getSource());

        return model;
    }
}
