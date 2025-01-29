package com.planner.domain.participant;

public record AddTripParticipantInput(
        String name,
        String email,
        boolean isConfirmed
) {}
