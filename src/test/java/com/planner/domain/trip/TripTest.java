package com.planner.domain.trip;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void should_create_trip_entity() {
        UUID ownerId = UUID.randomUUID();

        UUID tripId = UUID.randomUUID();

        Trip trip = Trip.create(
                tripId,
                ownerId,
                "Test Name",
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNotNull(trip);
        assertEquals(tripId, trip.getId());
    }

    @Test
    void should_create_trip_entity_with_null_id_as_null() {
        UUID ownerId = UUID.randomUUID();

        Trip trip = Trip.create(
                null,
                ownerId,
                "Test Name",
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        assertNotNull(trip.getId());
        assertNotNull(trip);

    }


}