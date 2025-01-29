package com.planner.application.usecases;

import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.usecases.RemoveParticipantUseCase;

import java.util.UUID;

public class RemoveParticipantUseCaseImpl implements RemoveParticipantUseCase {

    private ParticipantRepository participantRepository;

    public RemoveParticipantUseCaseImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public void execute(UUID participantId, UUID tripId) {
        participantRepository.deleteParticipant(participantId, tripId);
    }
}
