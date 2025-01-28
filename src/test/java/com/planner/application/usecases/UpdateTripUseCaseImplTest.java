package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.exceptions.TripInvalidDateException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.Trip;
import com.planner.domain.trip.UpdateTripInput;
import com.planner.domain.trip.UpdateTripOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;


class UpdateTripUseCaseImplTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private UpdateTripUseCaseImpl updateTripUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }


    @Test
    void should_update_trip_by_id() {
        UUID tripId = UUID.randomUUID();
        Trip trip = Trip.create(
                tripId,
                UUID.randomUUID(),
                "Test destination",
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        UpdateTripInput updateTripInput = new UpdateTripInput(
                "Destination Updated",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(5)
        );

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        UpdateTripOutput result = updateTripUseCase.execute(updateTripInput, tripId);

        assertNotNull(result);
        assertEquals(updateTripInput.destination(), result.destination());
    }

    @Test
    void should_throw_exception_if_trip_dates_does_not_valid() {
        UUID tripId = UUID.randomUUID();
        Trip trip = Trip.create(
                tripId,
                UUID.randomUUID(),
                "Test destination",
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        UpdateTripInput updateTripInput = new UpdateTripInput(
                "Destination Updated",
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(5)
        );

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));

        assertThrows(TripInvalidDateException.class, () ->
                updateTripUseCase.execute(updateTripInput, tripId)
        );

        verify(tripRepository, never()).save(any(Trip.class));
    }

    @Test
    void should_throw_exception_if_invalid_trip_id() {
        UUID tripId = UUID.randomUUID();
        UpdateTripInput updateTripInput = new UpdateTripInput(
                "Destination Updated",
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(5)
        );

        when(tripRepository.findById(tripId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                updateTripUseCase.execute(updateTripInput, tripId)
                );

        verify(tripRepository, never()).save(any(Trip.class));

    }
}