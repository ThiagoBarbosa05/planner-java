package com.planner.domain.participant;

import java.util.List;

public record InviteParticipantsOutput(
        List<ParticipantDTO> participants
) {}
