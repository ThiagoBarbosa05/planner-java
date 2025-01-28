package com.planner.application.usecases;

import com.planner.application.repositories.ParticipantRepository;
import com.planner.domain.participant.ListTripParticipantsOutput;
import com.planner.domain.participant.Participant;
import com.planner.domain.participant.ParticipantDTO;
import com.planner.domain.participant.usecases.ListTripParticipants;

import java.util.List;
import java.util.UUID;

public class ListTripParticipantsUseCaseImpl implements ListTripParticipants {

    private ParticipantRepository participantRepository;

    public ListTripParticipantsUseCaseImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ListTripParticipantsOutput execute(UUID tripId) {
        List<Participant> participantList = participantRepository.findManyByTripId(tripId);

        return new ListTripParticipantsOutput(
                participantList.stream()
                        .map(participant ->
                            new ParticipantDTO(
                                    participant.getId(),
                                    participant.getTripId(),
                                    participant.getName(),
                                    participant.getEmail(),
                                    participant.isConfirmed()
                            )
                        )
                        .toList()
        );
    }
}
