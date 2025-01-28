package com.planner.domain.owner;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerTest {

    @Test
    void should_create_owner_entity() {
        UUID ownerId = UUID.randomUUID();
        Owner owner = Owner.create(ownerId, "Test Name", "test@email.com");

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void should_create_owner_entity_with_null_id_as_null() {
        String ownerName = "Test Name";
        Owner owner = Owner.create(null, ownerName, "test@email.com");

        assertNotNull(owner.getId());
        assertEquals(ownerName, owner.getName());
    }

    @Test
    void should_update_owner() {
        String ownerNameToUpdate = "Test Name updated";
        Owner owner = Owner.create(null, "Test name", "test@email.com");

        owner.update(ownerNameToUpdate);


        assertEquals(ownerNameToUpdate, owner.getName());
    }
}