package com.planner.application.usecases;

import com.planner.application.exceptions.OwnerAlreadyExistsException;
import com.planner.application.repositories.OwnerRepository;
import com.planner.domain.owner.CreateOwnerInput;
import com.planner.domain.owner.CreateOwnerOutput;
import com.planner.domain.owner.Owner;
import com.planner.domain.owner.usecases.CreateOwnerUseCase;

import java.util.Optional;

public class CreateOwnerUseCaseImpl implements CreateOwnerUseCase {

    private OwnerRepository ownerRepository;

    public CreateOwnerUseCaseImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Override
    public CreateOwnerOutput execute(CreateOwnerInput createOwnerInput) {
        Optional<Owner> existingOwner = this.ownerRepository.findByEmail(createOwnerInput.email());

        if(existingOwner.isPresent()) {
            throw new OwnerAlreadyExistsException("Owner Already exists");
        }

        Owner owner = Owner.create(
                null,
                createOwnerInput.name(),
                createOwnerInput.email()
        );

        Owner ownerSaved = ownerRepository.save(owner);

        return new CreateOwnerOutput(
                ownerSaved.getId(),
                ownerSaved.getName(),
                ownerSaved.getEmail()
        );
    }
}
