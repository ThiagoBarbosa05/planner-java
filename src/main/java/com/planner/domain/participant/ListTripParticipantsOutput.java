package com.planner.domain.participant;

import java.util.List;

public record ListTripParticipantsOutput(
        List<ParticipantDTO> participants
) {
}
