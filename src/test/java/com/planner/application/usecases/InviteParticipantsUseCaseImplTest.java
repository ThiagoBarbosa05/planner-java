package com.planner.application.usecases;

import com.planner.application.notification.NotificationService;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.InviteParticipantsInput;
import com.planner.domain.participant.InviteParticipantsOutput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class InviteParticipantsUseCaseImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private InviteParticipantsUseCaseImpl inviteParticipantsUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_invite_participants_to_a_trip() {
        UUID tripId = UUID.randomUUID();
        UUID participantId = UUID.randomUUID();
        Participant participantToInvite = Participant.create(
                participantId,
                tripId,
                "John Doe",
                "john@example.com",
                false
        );

        InviteParticipantsInput input = new InviteParticipantsInput(
                List.of(participantToInvite)
                        .stream()
                        .map(participant -> new ParticipantDTO(
                                participant.getId(),
                                participant.getTripId(),
                                participant.getName(),
                                participant.getEmail(),
                                participant.isConfirmed()
                        ))
                        .toList()
        );

        when(participantRepository.saveAll(anyList())).thenReturn(List.of(participantToInvite));

        // When
        InviteParticipantsOutput result = inviteParticipantsUseCase.execute(input);

        // Then
        assertNotNull(result);
        assertEquals(1, result.participants().size());
        verify(participantRepository, times(1)).saveAll(anyList());
        verify(notificationService, times(1)).sendNotification(
                eq("john@example.com"), any(String.class), any(String.class)
        );
    }
}