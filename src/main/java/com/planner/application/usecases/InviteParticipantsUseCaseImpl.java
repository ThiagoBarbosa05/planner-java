package com.planner.application.usecases;

import com.planner.application.notification.NotificationService;
import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.InviteParticipantsInput;
import com.planner.domain.participant.InviteParticipantsOutput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import com.planner.domain.participant.usecases.InviteParticipantsUseCase;

import java.util.List;

public class InviteParticipantsUseCaseImpl implements InviteParticipantsUseCase {

    private ParticipantRepository participantRepository;
    private NotificationService notificationService;

    public InviteParticipantsUseCaseImpl(ParticipantRepository participantRepository, NotificationService notificationService) {
        this.participantRepository = participantRepository;
        this.notificationService = notificationService;
    }

    @Override
    public InviteParticipantsOutput execute(InviteParticipantsInput inviteParticipantInput) {

        List<Participant> participantsSaved = participantRepository.saveAll(
                inviteParticipantInput.participants().stream()
                        .map(participant -> Participant.create(
                                participant.id(),
                                participant.tripId(),
                                participant.name(),
                                participant.email(),
                                participant.isConfirmed()
                        )).toList()
        );

        participantsSaved.forEach(participant -> {
            notificationService.sendNotification(
                    participant.getEmail(),
                    "Invite to trip",
                    "You are invited to a trip"
            );
        });

        List<ParticipantDTO> participantDTOList = participantsSaved.stream()
                .map(participant -> new ParticipantDTO(
                        participant.getId(),
                        participant.getTripId(),
                        participant.getName(),
                        participant.getEmail(),
                        participant.getIsConfirmed()
                ))
                .toList();


        return new InviteParticipantsOutput(participantDTOList);
    }
}
