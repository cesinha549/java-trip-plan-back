package com.travelplanner.feature.place.adapter.out.db;

import com.travelplanner.core.trip.adapter.out.db.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<TripEntity, String> {
}
