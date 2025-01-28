package com.planner.domain.participant;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    void should_create_participant_entity() {
        UUID participantId = UUID.randomUUID();
        UUID tripId = UUID.randomUUID();

        Participant participant = Participant.create(participantId, tripId,"Test Name", "test@email.com", false);

        assertNotNull(participant);
        assertEquals(participantId, participant.getId());
    }

    @Test
    void should_create_participant_entity_with_null_id_as_null() {
        String participantName = "Test Name";
        UUID tripId = UUID.randomUUID();

        Participant participant = Participant.create(null, tripId, participantName, "test@email.com", false);

        assertNotNull(participant.getId());
        assertEquals(participantName, participant.getName());
    }
}