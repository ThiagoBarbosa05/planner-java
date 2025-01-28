package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.OwnerRepository;
import com.planner.domain.owner.GetOwnerOutput;
import com.planner.domain.owner.Owner;
import com.planner.domain.owner.usecases.GetOwnerUseCase;

import java.util.UUID;

public class GetOwnerUseCaseImpl implements GetOwnerUseCase {

    private OwnerRepository ownerRepository;

    public GetOwnerUseCaseImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public GetOwnerOutput execute(UUID ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found"));

        return new GetOwnerOutput(
          owner.getId(),
          owner.getName(),
          owner.getEmail()
        );
    }
}
