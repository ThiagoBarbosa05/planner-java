package com.planner.domain.owner;

import java.util.UUID;

public record GetOwnerOutput(
        UUID ownerId,
        String name,
        String email
) {
}
