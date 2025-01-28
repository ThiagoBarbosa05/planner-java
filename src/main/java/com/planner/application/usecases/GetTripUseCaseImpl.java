package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.GetTripOutput;
import com.planner.domain.trip.Trip;
import com.planner.domain.trip.usecases.GetTripUseCase;

import java.util.UUID;

public class GetTripUseCaseImpl implements GetTripUseCase {

    private TripRepository tripRepository;

    public GetTripUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public GetTripOutput execute(UUID tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        return new GetTripOutput(
          trip.getId(),
          trip.getOwnerId(),
          trip.getDestination(),
          trip.getStartDate(),
          trip.getEndDate()
        );
    }
}

