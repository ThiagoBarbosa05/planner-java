package com.planner.domain.owner;

import java.util.UUID;

public class Owner {
    private UUID id;
    private String name;
    private String email;

    private Owner(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static Owner create(UUID id, String name, String email) {
        UUID ownerId = UUID.randomUUID();

        if(id == null) {
            id = ownerId;
        }

        return new Owner(id, name, email);
    }

    public void update(String name) {
        this.name = name;
    }
}
