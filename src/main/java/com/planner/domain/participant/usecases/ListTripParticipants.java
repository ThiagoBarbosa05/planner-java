package com.planner.domain.participant.usecases;

import com.planner.domain.participant.ListTripParticipantsOutput;

import java.util.UUID;

public interface ListTripParticipants {
    ListTripParticipantsOutput execute(UUID tripId);
}
