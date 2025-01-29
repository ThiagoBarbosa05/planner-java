package com.planner.application.usecases;

import com.planner.application.exceptions.ParticipantAlreadyExistsException;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.CreateParticipantInput;
import com.planner.domain.participant.CreateParticipantOutput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.usecases.CreateParticipantUseCase;

import java.util.Optional;
import java.util.UUID;

public class CreateParticipantUseCaseImpl implements CreateParticipantUseCase {

    private ParticipantRepository participantRepository;

    public CreateParticipantUseCaseImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public CreateParticipantOutput execute(CreateParticipantInput createParticipantInput, UUID tripId) {
        Optional<Participant> existingParticipant = this.participantRepository.findByEmail(createParticipantInput.email());

        if(existingParticipant.isPresent()) {
            throw new ParticipantAlreadyExistsException("Participant Already exists");
        }

        Participant participant = Participant.create(
                null,
                tripId,
                createParticipantInput.name(),
                createParticipantInput.email(),
                false
        );

        Participant participantSaved = participantRepository.save(participant);

        return new CreateParticipantOutput(
                participantSaved.getId(),
                participantSaved.getTripId(),
                participantSaved.getName(),
                participantSaved.getEmail(),
                participantSaved.getIsConfirmed()
        );
    }
}
