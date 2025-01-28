package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.OwnerRepository;
import com.planner.domain.owner.GetOwnerOutput;
import com.planner.domain.owner.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;


class GetOwnerUseCaseImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private GetOwnerUseCaseImpl getOwnerUseCase;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void should_return_owner_by_id() {
        UUID ownerId = UUID.randomUUID();
        Owner owner = Owner.create(ownerId, "TEST user", "test@email.com");

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));

        GetOwnerOutput result = getOwnerUseCase.execute(ownerId);

        assertNotNull(result);
        assertEquals(ownerId, result.ownerId());

        verify(ownerRepository, times(1)).findById(any(UUID.class));

    }

    @Test
    void should_throw_exception_if_owner_not_found() {
        UUID ownerId = UUID.randomUUID();

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            this.getOwnerUseCase.execute(ownerId);
        });
    }
}