package com.travelplanner.core.trip.adapter.out.db;

import com.travelplanner.core.trip.domain.model.DestinationModel;
import com.travelplanner.core.trip.domain.model.TripModel;
import com.travelplanner.core.trip.adapter.in.web.dto.create.TripRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TripMapper {

    public TripEntity toEntity(TripModel model) {

        TripEntity entity = new TripEntity();
        DestinationEntity destination = new DestinationEntity();
        destination.setCity(model.getDestination().getCity());
        destination.setCountry(model.getDestination().getCountry());
        destination.setState(model.getDestination().getState());
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDestination(destination);
        entity.setStartDate(model.getStartDate());
        entity.setEndDate(model.getEndDate());
        entity.setBudget(model.getBudget());
        return entity;
    }

    public TripModel toModel(TripEntity entity) {
        TripModel model = new TripModel();
        DestinationModel destination = new DestinationModel();
        destination.setCity(entity.getDestination().getCity());
        destination.setCountry(entity.getDestination().getCountry());
        destination.setState(entity.getDestination().getState());
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDestination(destination);
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setBudget(entity.getBudget());
        return model;
    }

    public static TripModel fromRequestDTO(TripRequestDTO dto) {
        TripModel trip = new TripModel();
        trip.setName(dto.getName());
        trip.setStartDate(dto.getStartDate());
        trip.setEndDate(dto.getEndDate());

        DestinationModel destination = new DestinationModel();
        destination.setCity(dto.getCity());
        destination.setState(dto.getState());
        destination.setCountry(dto.getCountry());
        trip.setDestination(destination);

        // You can set budget and id later if needed
        return trip;
    }

    public TripPlaceEntity toTripPlace(TripEntity trip, String placeId, Integer order, LocalDate visitDate, String notes) {
        return TripPlaceEntity.builder()
                .trip(trip)
                .placeId(placeId)
                .orderInTrip(order)
                .visitDate(visitDate)
                .notes(notes)
                .build();
    }
}
