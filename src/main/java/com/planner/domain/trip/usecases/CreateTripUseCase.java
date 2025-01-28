package com.planner.domain.trip.usecases;

import com.planner.domain.trip.CreateTripInput;
import com.planner.domain.trip.CreateTripOutput;

import java.util.UUID;

public interface CreateTripUseCase {
    public CreateTripOutput execute(CreateTripInput createTripInput, UUID ownerId);
}
