package com.planner.domain.participant;

import java.util.List;

public record InviteParticipantsInput(
        List<ParticipantDTO> participants
) {}
