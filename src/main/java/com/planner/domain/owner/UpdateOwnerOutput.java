package com.planner.domain.owner;

import java.util.UUID;

public record UpdateOwnerOutput(
        UUID id,
        String name,
        String email
) {
}
