package com.travelplanner.core.place.domain.port.out.db;

import com.travelplanner.core.place.adapter.out.db.PlaceEntity;
import com.travelplanner.core.place.domain.model.PlaceModel;

import java.util.List;
import java.util.Optional;

public interface PlacePersistencePort {

    List<PlaceModel> save(List<PlaceModel> places); // mudou para retornar uma lista
    Optional<PlaceModel> findById(String id); // suporte para getPlace
    Optional<PlaceEntity> findByIdEntity(String id); // suporte para getPlace

}
