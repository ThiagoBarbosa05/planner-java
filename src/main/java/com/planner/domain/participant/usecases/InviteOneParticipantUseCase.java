package com.planner.domain.participant.usecases;

import com.planner.domain.participant.InviteOneParticipantInput;
import com.planner.domain.participant.ParticipantDTO;

import java.util.UUID;

public interface InviteOneParticipantUseCase {
    ParticipantDTO execute(InviteOneParticipantInput inviteOneParticipantInput, UUID tripId);
}
