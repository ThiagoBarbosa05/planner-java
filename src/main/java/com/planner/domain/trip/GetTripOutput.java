package com.planner.domain.trip;

import java.time.LocalDate;
import java.util.UUID;

public record GetTripOutput(
        UUID id,
        UUID ownerId,
        String destination,
        LocalDate startDate,
        LocalDate endDate
) {}
