package com.planner.application.usecases;

import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.ListTripParticipantsOutput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;


class ListTripParticipantsUseCaseImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ListTripParticipantsUseCaseImpl listTripParticipantsUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_list_all_participants_by_trip_id() {
        UUID tripId = UUID.randomUUID();

        List<Participant> participants = Arrays.asList(
                 Participant.create(UUID.randomUUID(), tripId, "John Doe", "john.doe@example.com", true),
                 Participant.create(UUID.randomUUID(), tripId, "Jane Doe", "jane.doe@example.com", false)
        );

        when(participantRepository.findManyByTripId(tripId)).thenReturn(participants);


        ListTripParticipantsOutput result = listTripParticipantsUseCase.execute(tripId);

        assertEquals(2, result.participants().size());

        ParticipantDTO firstParticipant = result.participants().get(0);
        assertEquals("John Doe", firstParticipant.name());
        assertEquals("john.doe@example.com", firstParticipant.email());
        assertEquals(true, firstParticipant.isConfirmed());

        ParticipantDTO secondParticipant = result.participants().get(1);
        assertEquals("Jane Doe", secondParticipant.name());
        assertEquals("jane.doe@example.com", secondParticipant.email());
        assertEquals(false, secondParticipant.isConfirmed());

        verify(participantRepository, times(1)).findManyByTripId(tripId);
    }
}