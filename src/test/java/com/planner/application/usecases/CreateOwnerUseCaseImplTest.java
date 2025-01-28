package com.planner.application.usecases;

import com.planner.application.exceptions.OwnerAlreadyExistsException;
import com.planner.application.repositories.OwnerRepository;
import com.planner.domain.owner.CreateOwnerInput;
import com.planner.domain.owner.CreateOwnerOutput;
import com.planner.domain.owner.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;


class CreateOwnerUseCaseImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private CreateOwnerUseCaseImpl createOwnerUseCase;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void should_return_owner_when_created() {
       CreateOwnerInput createOwnerInput = new CreateOwnerInput(
               "TEST name",
               "test@email.com"
       );
       Owner ownerToSave = Owner.create(UUID.randomUUID(), createOwnerInput.name(), createOwnerInput.email());

       when(ownerRepository.findByEmail(createOwnerInput.email())).thenReturn(Optional.empty());
       when(ownerRepository.save(any(Owner.class))).thenReturn(ownerToSave);

       CreateOwnerOutput result = createOwnerUseCase.execute(createOwnerInput);

       assertNotNull(result);
       assertEquals( ownerToSave.getName(), result.name());
       assertEquals(ownerToSave.getId(), result.ownerId());
       verify(ownerRepository, times(1)).findByEmail(any(String.class));
       verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void should_throw_exception_if_owner_exists() {
        CreateOwnerInput createOwnerInput = new CreateOwnerInput(
                "TEST name",
                "test@email.com"
        );
        Owner existingOwner = Owner.create(UUID.randomUUID(), createOwnerInput.name(), createOwnerInput.email());

        when(ownerRepository.findByEmail(createOwnerInput.email())).thenReturn(Optional.of(existingOwner));

        assertThrows(OwnerAlreadyExistsException.class, () -> {
            this.createOwnerUseCase.execute(createOwnerInput);
        });

        verify(ownerRepository, times(1)).findByEmail(any(String.class));
        verify(ownerRepository, never()).save(any(Owner.class));
    }
}