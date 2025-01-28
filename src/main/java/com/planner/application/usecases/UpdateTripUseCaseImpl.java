package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.exceptions.TripInvalidDateException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.Trip;
import com.planner.domain.trip.UpdateTripInput;
import com.planner.domain.trip.UpdateTripOutput;
import com.planner.domain.trip.usecases.UpdateTripUseCase;

import java.util.UUID;

public class UpdateTripUseCaseImpl implements UpdateTripUseCase {

    private TripRepository tripRepository;

    public UpdateTripUseCaseImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public UpdateTripOutput execute(UpdateTripInput updateTripInput, UUID tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        trip.update(updateTripInput.destination(), updateTripInput.startDate(), updateTripInput.endDate());

        if(trip.isStartDateBeforeCurrentDate()) {
            throw new TripInvalidDateException("Start date cannot be before current date");
        }

        if(trip.isEndDateBeforeStartDate()) {
            throw new TripInvalidDateException("End date cannot be bedore start date");
        }

        Trip tripUpdated = tripRepository.save(trip);

        return new UpdateTripOutput(
                tripUpdated.getId(),
                tripUpdated.getOwnerId(),
                tripUpdated.getDestination(),
                tripUpdated.getStartDate(),
                tripUpdated.getEndDate()
        );
    }
}
