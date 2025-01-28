package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.GetParticipantOutput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.usecases.GetParticipantUseCase;

import java.util.UUID;

public class GetParticipantUseCaseImpl implements GetParticipantUseCase {

    private ParticipantRepository participantRepository;

    public GetParticipantUseCaseImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public GetParticipantOutput execute(UUID participantId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));

        return new GetParticipantOutput(
                participant.getId(),
                participant.getTripId(),
                participant.getName(),
                participant.getEmail(),
                participant.isConfirmed()
        );
    }
}
