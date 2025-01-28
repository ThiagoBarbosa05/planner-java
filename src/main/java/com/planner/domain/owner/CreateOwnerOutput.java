package com.planner.domain.owner;

import java.util.UUID;

public record CreateOwnerOutput(
        UUID ownerId,
        String name,
        String email
) {
}
