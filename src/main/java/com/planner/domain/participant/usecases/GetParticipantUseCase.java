package com.planner.domain.participant.usecases;

import com.planner.domain.participant.GetParticipantOutput;

import java.util.UUID;

public interface GetParticipantUseCase {
    GetParticipantOutput execute(UUID participantId);
}
