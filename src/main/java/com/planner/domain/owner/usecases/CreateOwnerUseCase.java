package com.planner.domain.owner.usecases;

import com.planner.domain.owner.CreateOwnerInput;
import com.planner.domain.owner.CreateOwnerOutput;


public interface CreateOwnerUseCase {
    public CreateOwnerOutput execute(CreateOwnerInput createOwnerInput);
}
