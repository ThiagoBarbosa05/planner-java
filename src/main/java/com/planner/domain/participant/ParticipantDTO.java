package com.planner.domain.participant;

import java.util.UUID;

public record ParticipantDTO(
        UUID id,
        UUID tripId,
        String name,
        String email,
        boolean isConfirmed
) {}
