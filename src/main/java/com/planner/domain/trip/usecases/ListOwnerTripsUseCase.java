package com.planner.domain.trip.usecases;

import com.planner.domain.trip.ListOwnerTripsOutput;

import java.util.UUID;

public interface ListOwnerTripsUseCase {
    ListOwnerTripsOutput execute(UUID ownerId);
}
