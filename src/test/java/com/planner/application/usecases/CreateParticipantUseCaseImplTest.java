package com.planner.application.usecases;

import com.planner.application.exceptions.ParticipantAlreadyExistsException;
import com.planner.application.repositories.ParticipantRepository;

import com.planner.domain.participant.CreateParticipantInput;
import com.planner.domain.participant.CreateParticipantOutput;
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
import static org.mockito.MockitoAnnotations.openMocks;

class CreateParticipantUseCaseImplTest {

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private CreateParticipantUseCaseImpl createParticipantUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_return_participant_when_created() {
        CreateParticipantInput createParticipantInput = new CreateParticipantInput(
                "TEST name",
                "test@email.com"
        );
        UUID tripId = UUID.randomUUID();
        Participant participantToSave = Participant.create(UUID.randomUUID(), tripId, createParticipantInput.name(), createParticipantInput.email(), false);

        when(participantRepository.findByEmail(createParticipantInput.email())).thenReturn(Optional.empty());
        when(participantRepository.save(any(Participant.class))).thenReturn(participantToSave);

        CreateParticipantOutput result = createParticipantUseCase.execute(createParticipantInput, tripId);

        assertNotNull(result);
        assertEquals(participantToSave.getName(), result.name());
        assertEquals( participantToSave.getId(), result.participantId());
        verify(participantRepository, times(1)).findByEmail(any(String.class));
        verify(participantRepository, times(1)).save(any(Participant.class));
    }

    @Test
    void should_throw_exception_if_participant_exists() {
        CreateParticipantInput createParticipantInput = new CreateParticipantInput(
                "TEST name",
                "test@email.com"
        );
        UUID tripId = UUID.randomUUID();

        Participant existingParticipant = Participant.create(UUID.randomUUID(), tripId, createParticipantInput.name(), createParticipantInput.email(), false);

        when(participantRepository.findByEmail(createParticipantInput.email())).thenReturn(Optional.of(existingParticipant));

        assertThrows(ParticipantAlreadyExistsException.class, () -> {
            this.createParticipantUseCase.execute(createParticipantInput, tripId);
        });

        verify(participantRepository, times(1)).findByEmail(any(String.class));
        verify(participantRepository, never()).save(any(Participant.class));
    }
}