package com.planner.domain.participant;

public record InviteOneParticipantInput(
        String name,
        String email,
        boolean isConfirmed
) {}
