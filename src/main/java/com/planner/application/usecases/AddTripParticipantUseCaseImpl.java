package com.planner.application.usecases;

import com.planner.application.exceptions.ParticipantAlreadyExistsException;
import com.planner.application.notification.NotificationService;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.AddTripParticipantInput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import com.planner.domain.participant.usecases.AddTripParticipant;

import java.util.Optional;
import java.util.UUID;

public class AddTripParticipantUseCaseImpl implements AddTripParticipant {

    private ParticipantRepository participantRepository;
    private NotificationService notificationService;

    public AddTripParticipantUseCaseImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ParticipantDTO execute(AddTripParticipantInput addTripParticipantInput, UUID tripId) {
        Optional<Participant> existingParticipantOnTrip = participantRepository.findByEmailAndTripId(addTripParticipantInput.email(), tripId);

        if(existingParticipantOnTrip.isPresent()) {
            throw new ParticipantAlreadyExistsException("This participant has already been invited to this trip");
        }

        Participant newParticipant = Participant.create(
                    UUID.randomUUID(),
                tripId,
                addTripParticipantInput.name(),
                addTripParticipantInput.email(),
                addTripParticipantInput.isConfirmed()
        );

        Participant participantInvited = participantRepository.save(newParticipant);

        return new ParticipantDTO(
                participantInvited.getId(),
                participantInvited.getTripId(),
                participantInvited.getName(),
                participantInvited.getEmail(),
                participantInvited.isConfirmed()
        );
    }
}
