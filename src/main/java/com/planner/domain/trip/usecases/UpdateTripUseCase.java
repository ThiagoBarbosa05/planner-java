package com.planner.domain.trip.usecases;

import com.planner.domain.trip.UpdateTripInput;
import com.planner.domain.trip.UpdateTripOutput;

import java.util.UUID;

public interface UpdateTripUseCase {
    UpdateTripOutput execute(UpdateTripInput updateTripInput, UUID tripId);
}
