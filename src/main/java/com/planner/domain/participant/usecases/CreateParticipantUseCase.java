package com.planner.domain.participant.usecases;

import com.planner.domain.participant.CreateParticipantInput;
import com.planner.domain.participant.CreateParticipantOutput;

import java.util.UUID;

public interface CreateParticipantUseCase {
    public CreateParticipantOutput execute(CreateParticipantInput createParticipantInput, UUID tripId);
}
