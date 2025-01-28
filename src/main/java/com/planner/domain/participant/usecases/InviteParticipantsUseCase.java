package com.planner.domain.participant.usecases;

import com.planner.domain.participant.InviteParticipantsInput;
import com.planner.domain.participant.InviteParticipantsOutput;

public interface InviteParticipantsUseCase {
    InviteParticipantsOutput execute(InviteParticipantsInput inviteParticipantInput);
}
