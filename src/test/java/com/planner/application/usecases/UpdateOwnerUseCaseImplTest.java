package com.planner.application.usecases;

import com.planner.application.exceptions.ResourceNotFoundException;
import com.planner.application.repositories.OwnerRepository;
import com.planner.domain.owner.Owner;
import com.planner.domain.owner.UpdateOwnerInput;
import com.planner.domain.owner.UpdateOwnerOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.*;



class UpdateOwnerUseCaseImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private UpdateOwnerUseCaseImpl updateOwnerUseCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void should_update_owner() {
        Owner owner = Owner.create(UUID.randomUUID(), "TEST owner", "test@email.com");
        UpdateOwnerInput updateOwnerInput = new UpdateOwnerInput("TEST owner updated");

        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        UpdateOwnerOutput result = this.updateOwnerUseCase.execute(updateOwnerInput, owner.getId());

        assertNotNull(result);
        assertEquals(updateOwnerInput.name(), result.name());

        verify(ownerRepository, times(1)).findById(any(UUID.class));
        verify(ownerRepository, times(1)).save(any(Owner.class));

    }

    @Test
    void should_throw_exception_if_owner_not_found() {
        UUID ownerId = UUID.randomUUID();
        UpdateOwnerInput updateOwnerInput = new UpdateOwnerInput("TEST owner updated");

        when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> this.updateOwnerUseCase.execute(updateOwnerInput, ownerId));

        verify(ownerRepository, times(1)).findById(any(UUID.class));
        verify(ownerRepository, never()).save(any(Owner.class));

    }
}