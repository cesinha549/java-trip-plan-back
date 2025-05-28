package com.travelplanner.feature.place.adapter.out.db;

import com.travelplanner.feature.place.domain.model.PlaceModel;
import com.travelplanner.feature.place.domain.port.out.db.PlacePersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlacePersistenceAdapter implements PlacePersistencePort {


    private final PlaceRepository placeRepository;

    public PlacePersistenceAdapter(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<PlaceModel> save(List<PlaceModel> models) {
        List<PlaceEntity> entities = models.stream()
                .map(PlaceMapper::toEntity)
                .toList();

        List<PlaceEntity> savedEntities = placeRepository.saveAll(entities);

        return savedEntities.stream()
                .map(PlaceMapper::toModel)
                .toList();
    }

    @Override
    public Optional<PlaceModel> findById(String id) {
        return placeRepository.findById(id)
                .map(PlaceMapper::toModel);
    }


    public Optional<PlaceEntity> findByIdEntity(String id) {
        return placeRepository.findById(id);
    }
}
