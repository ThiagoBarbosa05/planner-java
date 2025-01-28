package com.planner.domain.owner.usecases;

import com.planner.domain.owner.GetOwnerOutput;

import java.util.UUID;

public interface GetOwnerUseCase {
    GetOwnerOutput execute(UUID ownerId);
}
