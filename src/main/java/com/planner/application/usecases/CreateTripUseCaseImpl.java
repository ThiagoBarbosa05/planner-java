package com.planner.application.usecases;

import com.planner.application.exceptions.TripInvalidDateException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.CreateTripInput;
import com.planner.domain.trip.CreateTripOutput;
import com.planner.domain.trip.Trip;
import com.planner.domain.trip.usecases.CreateTripUseCase;

import java.util.UUID;

public class CreateTripUseCaseImpl implements CreateTripUseCase {

    private TripRepository tripRepository;

    public CreateTripUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public CreateTripOutput execute(CreateTripInput createTripInput, UUID ownerId) {
        Trip trip = Trip.create(
                UUID.randomUUID(),
                ownerId,
                createTripInput.destination(),
                createTripInput.startDate(),
                createTripInput.endDate()
        );

        if (trip.isStartDateBeforeCurrentDate()) {
            throw new TripInvalidDateException("Start date cannot be before current date");
        }

        if(trip.isEndDateBeforeStartDate()) {
            throw new TripInvalidDateException("End date cannot be before the start date");
        }

        Trip tripSaved = tripRepository.save(trip);

        return new CreateTripOutput(
                tripSaved.getId(),
                ownerId,
                tripSaved.getDestination(),
                tripSaved.getStartDate(),
                tripSaved.getEndDate()
        );
    }
}
