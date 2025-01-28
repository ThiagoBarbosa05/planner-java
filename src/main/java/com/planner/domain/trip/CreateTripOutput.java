package com.planner.domain.trip;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTripOutput(
        UUID id,
        UUID ownerId,
        String destination,
        LocalDate startDate,
        LocalDate endDate
) {
}
