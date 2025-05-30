package com.travelplanner.core.trip.config;

import com.travelplanner.core.trip.adapter.out.db.TripMapper;
import com.travelplanner.core.trip.adapter.out.db.TripPersistenceAdapter;
import com.travelplanner.core.trip.adapter.out.db.TripPlaceRepository;
import com.travelplanner.core.trip.adapter.out.db.TripRepository;
import com.travelplanner.core.trip.domain.port.out.TripPersistencePort;
import com.travelplanner.core.trip.domain.port.out.TripQueryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TripBeanConfiguration {


    @Bean
    public TripPersistenceAdapter tripPersistenceAdapter(TripRepository tripRepository, TripMapper mapper, TripPlaceRepository tripPlaceRepository) {
        return new TripPersistenceAdapter(tripRepository, mapper,tripPlaceRepository);
    }

    @Bean
    public TripQueryPort tripQueryPort(TripPersistenceAdapter adapter) {
        return adapter;
    }

    @Bean
    public TripPersistencePort tripPersistencePort(TripPersistenceAdapter adapter) {
        return adapter;
    }
}
