package com.planner.domain.trip.usecases;

import com.planner.domain.trip.GetTripOutput;

import java.util.UUID;

public interface GetTripUseCase {
    GetTripOutput execute(UUID tripId);
}
