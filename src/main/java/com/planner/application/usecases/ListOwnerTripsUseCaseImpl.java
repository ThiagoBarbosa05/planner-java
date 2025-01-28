package com.planner.application.usecases;

import com.planner.application.repositories.TripRepository;

import com.planner.domain.trip.ListOwnerTripsOutput;
import com.planner.domain.trip.Trip;
import com.planner.domain.trip.TripDTO;
import com.planner.domain.trip.usecases.ListOwnerTripsUseCase;

import java.util.List;
import java.util.UUID;

public class ListOwnerTripsUseCaseImpl implements ListOwnerTripsUseCase {

    private TripRepository tripRepository;

    public ListOwnerTripsUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public ListOwnerTripsOutput execute(UUID ownerId) {
        List<Trip> tripList = tripRepository.findManyByOwnerId(ownerId);

        List<TripDTO> tripDTOList = tripList.stream()
                .map(trip -> new TripDTO(
                        trip.getId(),
                        trip.getOwnerId(),
                        trip.getDestination(),
                        trip.getStartDate(),
                        trip.getEndDate()
                ))
                .toList();

        return new ListOwnerTripsOutput(tripDTOList);
    }
}
