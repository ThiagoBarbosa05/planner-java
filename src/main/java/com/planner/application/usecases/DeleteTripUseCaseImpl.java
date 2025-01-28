package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.Trip;
import com.planner.domain.trip.usecases.DeleteTripUseCase;

import java.util.UUID;

public class DeleteTripUseCaseImpl implements DeleteTripUseCase  {

    private TripRepository tripRepository;

    public DeleteTripUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void execute(UUID tripId) {
        tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        tripRepository.delete(tripId);
    }
}
