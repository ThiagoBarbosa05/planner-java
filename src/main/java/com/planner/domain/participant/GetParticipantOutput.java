package com.planner.domain.participant;

import java.util.UUID;

public record GetParticipantOutput(
        UUID participantId,
        UUID tripId,
        String name,
        String email,
        boolean isConfirmed
) {
}
