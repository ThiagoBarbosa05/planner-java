package com.planner.domain.participant.usecases;

import java.util.UUID;

public interface RemoveTripParticipant {
    void execute(UUID tripId);
}
