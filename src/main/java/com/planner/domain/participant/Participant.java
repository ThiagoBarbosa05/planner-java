package com.planner.domain.participant;

import java.util.UUID;

public class Participant {
    private UUID id;
    private UUID tripId;
    private String name;
    private String email;
    private boolean isConfirmed;

    private Participant(UUID id, UUID tripId,String name, String email, boolean isConfirmed) {
        this.id = id;
        this.tripId = tripId;
        this.name = name;
        this.email = email;
        this.isConfirmed = isConfirmed;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTripId() {
        return tripId;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsConfirmed() { return isConfirmed; }

    public void confirmPresence(String name) {
        if(name != null) {
            this.name = name;
        }
        this.isConfirmed = true;
    }

    public static Participant create(UUID id, UUID tripId, String name, String email, boolean isConfirmed) {
        UUID participantId = UUID.randomUUID();

        if(id == null) {
            id = participantId;
        }

        return new Participant(id, tripId,name, email, isConfirmed);
    }
}
