package com.planner.domain.participant.usecases;

import java.util.UUID;

public interface RemoveParticipantUseCase {
    void execute(UUID participantId, UUID tripId);
}
