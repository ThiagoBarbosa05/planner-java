package com.planner.domain.participant.usecases;

import com.planner.domain.participant.ConfirmPresenceOnTripInput;
import com.planner.domain.participant.ParticipantDTO;

import java.util.UUID;

public interface ConfirmPresenceOnTripUseCase {
    ParticipantDTO execute(ConfirmPresenceOnTripInput confirmPresenceOnTripInput, UUID tripId);
}
