package com.planner.domain.trip;
import java.time.LocalDate;

public record CreateTripInput(
        String destination,
        LocalDate startDate,
        LocalDate endDate
) {
}
