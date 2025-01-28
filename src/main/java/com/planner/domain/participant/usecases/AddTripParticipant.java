package com.planner.domain.participant.usecases;

import com.planner.domain.participant.AddTripParticipantInput;
import com.planner.domain.participant.ParticipantDTO;

import java.util.UUID;

public interface AddTripParticipant {
    ParticipantDTO execute(AddTripParticipantInput addTripParticipantInput, UUID tripId);
}
