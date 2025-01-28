package com.planner.domain.trip.usecases;

import java.util.UUID;

public interface DeleteTripUseCase {
    void execute(UUID tripId);
}
