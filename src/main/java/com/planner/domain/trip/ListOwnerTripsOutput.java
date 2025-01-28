package com.planner.domain.trip;

import java.util.List;

public record ListOwnerTripsOutput(
        List<TripDTO> tripList
) {}
