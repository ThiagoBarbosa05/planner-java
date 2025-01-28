package com.planner.domain.trip;

import java.time.LocalDate;

public record UpdateTripInput(
        String destination,
        LocalDate startDate,
        LocalDate endDate
) {
}
