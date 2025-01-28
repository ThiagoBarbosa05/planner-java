package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.GetParticipantOutput;
import com.planner.domain.participant.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class GetParticipantUseCaseImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private GetParticipantUseCaseImpl getParticipantUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_return_participant_by_id() {
        UUID participantId = UUID.randomUUID();
        UUID tripId = UUID.randomUUID();

        Participant participant = Participant.create(participantId, tripId, "TEST user", "test@email.com", false);

        when(participantRepository.findById(participantId)).thenReturn(Optional.of(participant));

        GetParticipantOutput result = getParticipantUseCase.execute(participantId);

        assertNotNull(result);
        assertEquals(participantId, result.participantId());

        verify(participantRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    void should_throw_exception_if_participant_not_found() {
        UUID participantId = UUID.randomUUID();

        when(participantRepository.findById(participantId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            this.getParticipantUseCase.execute(participantId);
        });
    }
}