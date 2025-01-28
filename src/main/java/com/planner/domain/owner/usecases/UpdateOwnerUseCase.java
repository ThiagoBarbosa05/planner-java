package com.planner.domain.owner.usecases;

import com.planner.domain.owner.UpdateOwnerInput;
import com.planner.domain.owner.UpdateOwnerOutput;

import java.util.UUID;

public interface UpdateOwnerUseCase {
    public UpdateOwnerOutput execute(UpdateOwnerInput updateOwnerInput, UUID ownerId);
}
