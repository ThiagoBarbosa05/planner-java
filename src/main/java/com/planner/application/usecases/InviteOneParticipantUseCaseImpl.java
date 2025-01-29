package com.planner.application.usecases;

import com.planner.application.exceptions.ParticipantAlreadyExistsException;
import com.planner.application.notification.NotificationService;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.InviteOneParticipantInput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import com.planner.domain.participant.usecases.InviteOneParticipantUseCase;

import java.util.Optional;
import java.util.UUID;

public class InviteOneParticipantUseCaseImpl implements InviteOneParticipantUseCase {

    private ParticipantRepository participantRepository;
    private NotificationService notificationService;

    public InviteOneParticipantUseCaseImpl(ParticipantRepository participantRepository, NotificationService notificationService) {
        this.participantRepository = participantRepository;
        this.notificationService = notificationService;
    }

    @Override
    public ParticipantDTO execute(InviteOneParticipantInput inviteOneParticipantInput, UUID tripId) {
        Optional<Participant> existingParticipantOnTrip = participantRepository.findByEmailAndTripId(inviteOneParticipantInput.email(), tripId);

        if(existingParticipantOnTrip.isPresent()) {
            throw new ParticipantAlreadyExistsException("This participant has already been invited to this trip");
        }

        Participant newParticipant = Participant.create(
                    UUID.randomUUID(),
                tripId,
                inviteOneParticipantInput.name(),
                inviteOneParticipantInput.email(),
                inviteOneParticipantInput.isConfirmed()
        );

        Participant participantInvited = participantRepository.save(newParticipant);

        notificationService.sendNotification(
                participantInvited.getEmail(),
                "Trip Invite",
                "You were invited to a trip."
        );

        return new ParticipantDTO(
                participantInvited.getId(),
                participantInvited.getTripId(),
                participantInvited.getName(),
                participantInvited.getEmail(),
                participantInvited.getIsConfirmed()
        );
    }
}
