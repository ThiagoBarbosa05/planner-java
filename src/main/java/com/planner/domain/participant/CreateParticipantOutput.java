package com.planner.domain.participant;

import java.util.UUID;

public record CreateParticipantOutput(
        UUID participantId,
        UUID tripId,
        String name,
        String email,
        boolean isConfirmed
) {
}
