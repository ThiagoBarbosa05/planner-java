package com.planner.application.usecases;

import com.planner.application.exceptions.TripInvalidDateException;
import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.CreateTripInput;
import com.planner.domain.trip.CreateTripOutput;
import com.planner.domain.trip.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateTripUseCaseImplTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private CreateTripUseCaseImpl createTripUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_create_trip() {
        UUID ownerId = UUID.randomUUID();
        CreateTripInput createTripInput = new CreateTripInput(
                "statue of Liberty, USA",
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );
        Trip tripToSave = Trip.create(
                UUID.randomUUID(),
                ownerId,
                createTripInput.destination(),
                createTripInput.startDate(),
                createTripInput.endDate()
        );

        when(tripRepository.save(any(Trip.class))).thenReturn(tripToSave);

        CreateTripOutput result = createTripUseCase.execute(createTripInput, ownerId);

        assertNotNull(result);
        assertEquals(tripToSave.getDestination(), result.destination());

        verify(tripRepository, times(1)).save(any(Trip.class));
    }

    @Test
    void should_throw_exception_if_endDate_is_before_start_date() {

        UUID ownerId = UUID.randomUUID();

        CreateTripInput createTripInput = new CreateTripInput(
                "statue of Liberty, USA",
                LocalDate.now(),
                LocalDate.now().minusDays(2)
        );

        assertThrows(TripInvalidDateException.class,
                () -> createTripUseCase.execute(createTripInput, ownerId));

        verify(tripRepository, never()).save(any(Trip.class));
    }

    @Test
    void should_throw_exception_if_start_date_is_before_current_date() {
        UUID ownerId = UUID.randomUUID();

        CreateTripInput createTripInput = new CreateTripInput(
                "statue of Liberty, USA",
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(4)
        );

        assertThrows(TripInvalidDateException.class,
                () -> createTripUseCase.execute(createTripInput, ownerId));

        verify(tripRepository, never()).save(any(Trip.class));
    }
}