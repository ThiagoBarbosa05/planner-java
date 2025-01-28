package com.planner.application.repositories;

import com.planner.domain.owner.Owner;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository {
    Optional<Owner> findByEmail(String email);
    Owner save(Owner owner);
    Optional<Owner> findById(UUID id);
}
