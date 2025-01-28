package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DeleteTripUseCaseImplTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private DeleteTripUseCaseImpl deleteTripUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_delete_trip_by_id() {
        UUID tripId = UUID.randomUUID();
        Trip trip = Trip.create(
                tripId,
                UUID.randomUUID(),
                "Test destination",
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));

        deleteTripUseCase.execute(tripId);

        verify(tripRepository, times(1)).findById(tripId);
        verify(tripRepository, times(1)).delete(tripId);
    }

    @Test
    void should_throw_exception_if_trip_id_is_invalid() {
        UUID tripId = UUID.randomUUID();

        when(tripRepository.findById(tripId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                deleteTripUseCase.execute(tripId)
                );

        verify(tripRepository, times(1)).findById(tripId);
        verify(tripRepository, never()).delete(tripId);
    }
}