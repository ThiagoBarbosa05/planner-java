package com.planner.application.usecases;

import com.planner.application.repositories.ParticipantRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class RemoveParticipantUseCaseImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private RemoveParticipantUseCaseImpl removeParticipantUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_remove_participant_from_trip() {
        UUID tripId = UUID.randomUUID();
        UUID participantId = UUID.randomUUID();

        removeParticipantUseCase.execute(participantId, tripId);

        verify(participantRepository, times(1)).deleteParticipant(participantId, tripId);
    }
}