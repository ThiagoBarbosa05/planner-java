package com.planner.application.usecases;

import com.planner.application.repositories.TripRepository;
import com.planner.domain.trip.ListOwnerTripsOutput;
import com.planner.domain.trip.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;

class ListOwnerTripsUseCaseImplTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private ListOwnerTripsUseCaseImpl listOwnerTripsUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_return_a_list_of_trips_by_owner_id() {
        UUID ownerId = UUID.randomUUID();

        List<Trip> tripList = List.of(
                Trip.create(UUID.randomUUID(), ownerId, "destination 1", LocalDate.now(), LocalDate.now().plusDays(2)),
                Trip.create(UUID.randomUUID(), ownerId, "destination 2", LocalDate.now(), LocalDate.now().plusDays(3))
        );

        when(tripRepository.findManyByOwnerId(ownerId)).thenReturn(tripList);

        ListOwnerTripsOutput result = listOwnerTripsUseCase.execute(ownerId);

        assertEquals(2, result.tripList().size());
        verify(tripRepository, times(1)).findManyByOwnerId(ownerId);
    }
}