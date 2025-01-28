package com.planner.application.repositories;

import com.planner.domain.trip.Trip;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripRepository {
    Trip save(Trip trip);
    Optional<Trip> findById(UUID tripId);
    List<Trip> findManyByOwnerId(UUID ownerId);
    void delete(UUID tripId);
}
