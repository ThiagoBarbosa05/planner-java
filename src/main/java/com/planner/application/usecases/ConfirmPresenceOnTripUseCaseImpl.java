package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.ConfirmPresenceOnTripInput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import com.planner.domain.participant.usecases.ConfirmPresenceOnTripUseCase;

import java.util.UUID;

public class ConfirmPresenceOnTripUseCaseImpl implements ConfirmPresenceOnTripUseCase {

    private ParticipantRepository participantRepository;

    public ConfirmPresenceOnTripUseCaseImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ParticipantDTO execute(ConfirmPresenceOnTripInput confirmPresenceOnTripInput, UUID tripId) {
        Participant participant = participantRepository.findByEmailAndTripId(confirmPresenceOnTripInput.email(), tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Sorry, You weren't invited on this trip"));

        participant.confirmPresence(confirmPresenceOnTripInput.name());

        Participant participantConfirmed = participantRepository.save(participant);

        return new ParticipantDTO(
                participantConfirmed.getId(),
                participantConfirmed.getTripId(),
                participantConfirmed.getName(),
                participantConfirmed.getEmail(),
                participantConfirmed.getIsConfirmed()
        );
    }
}
