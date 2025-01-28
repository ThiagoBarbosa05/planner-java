package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.OwnerRepository;
import com.planner.domain.owner.Owner;
import com.planner.domain.owner.UpdateOwnerInput;
import com.planner.domain.owner.UpdateOwnerOutput;
import com.planner.domain.owner.usecases.UpdateOwnerUseCase;

import java.util.UUID;

public class UpdateOwnerUseCaseImpl implements UpdateOwnerUseCase {

    private OwnerRepository ownerRepository;

    public UpdateOwnerUseCaseImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UpdateOwnerOutput execute(UpdateOwnerInput updateOwnerInput, UUID ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        owner.update(updateOwnerInput.name());

        Owner ownerUpdated = this.ownerRepository.save(owner);

        return new UpdateOwnerOutput(
                ownerUpdated.getId(),
                ownerUpdated.getName(),
                ownerUpdated.getEmail()
        );
    }
}
