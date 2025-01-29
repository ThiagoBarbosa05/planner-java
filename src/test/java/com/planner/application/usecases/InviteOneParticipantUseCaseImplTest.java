package com.planner.application.usecases;

import com.planner.application.exceptions.ParticipantAlreadyExistsException;
import com.planner.application.notification.NotificationService;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.InviteOneParticipantInput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class InviteOneParticipantUseCaseImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private NotificationService notificationService;


    @InjectMocks
    private InviteOneParticipantUseCaseImpl inviteOneParticipantUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_invite_one_participant_for_trip() {
        UUID tripId = UUID.randomUUID();

        Participant participant = Participant.create(UUID.randomUUID(), tripId, "Jane Doe", "jane.doe@example.com", false);
        InviteOneParticipantInput inviteOneParticipantInput = new InviteOneParticipantInput(
                participant.getName(),
                participant.getEmail(),
                false
        );

        when(participantRepository.findByEmailAndTripId(participant.getEmail(), tripId)).thenReturn(Optional.empty());
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        ParticipantDTO result = inviteOneParticipantUseCase.execute(inviteOneParticipantInput, tripId);

        assertNotNull(result);
        assertEquals(participant.getEmail(), result.email());
        verify(participantRepository, times(1)).findByEmailAndTripId(participant.getEmail(), tripId);
        verify(participantRepository, times(1)).save(any(Participant.class));
        verify(notificationService, times(1)).sendNotification(
                anyString(), anyString(), anyString()
        );
    }

    @Test
    void should_throw_exception_if_participant_exists_on_trip() {
        UUID tripId = UUID.randomUUID();

        Participant participant = Participant.create(UUID.randomUUID(), tripId, "Jane Doe", "jane.doe@example.com", false);
        InviteOneParticipantInput inviteOneParticipantInput = new InviteOneParticipantInput(
                participant.getName(),
                participant.getEmail(),
                false
        );

        when(participantRepository.findByEmailAndTripId(participant.getEmail(), tripId)).thenReturn(Optional.of(participant));

        assertThrows(ParticipantAlreadyExistsException.class, () ->
                inviteOneParticipantUseCase.execute(inviteOneParticipantInput, tripId));

        verify(participantRepository, times(1)).findByEmailAndTripId(participant.getEmail(), tripId);
        verify(participantRepository, never()).save(any(Participant.class));
        verify(notificationService, never()).sendNotification(
                anyString(), anyString(), anyString()
        );
    }
}