package com.travelplanner.core.trip.adapter.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TripPlaceRepository extends JpaRepository<TripPlaceEntity, String> {
    // Optionally add: existsByTripAndPlace(...) if you want to avoid duplicates
}