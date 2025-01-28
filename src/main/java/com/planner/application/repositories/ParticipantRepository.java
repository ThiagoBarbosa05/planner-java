package com.planner.application.repositories;

import com.planner.domain.participant.Participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository {
    Participant save(Participant participant);
    Optional<Participant> findByEmail(String email);
    Optional<Participant> findById(UUID participantId);
    List<Participant> saveAll(List<Participant> participants);
    List<Participant> findManyByTripId(UUID tripId);
}
