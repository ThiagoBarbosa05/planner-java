package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.GetTripOutput;
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

class GetTripUseCaseImplTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private GetTripUseCaseImpl getTripUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_get_trip_by_id() {
        UUID tripId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();

        Trip trip = Trip.create(
                tripId,
                ownerId,
                "TEST destination",
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));

        GetTripOutput result = getTripUseCase.execute(tripId);

        assertNotNull(result);
        assertEquals( tripId, result.id());

        verify(tripRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void should_throw_exception_if_trip_not_found() {
        UUID tripId = UUID.randomUUID();

        when(tripRepository.findById(tripId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> getTripUseCase.execute(tripId));

        verify(tripRepository, times(1)).findById(any(UUID.class));
    }
}