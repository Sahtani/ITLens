package com.youcode.itlens;

import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import com.youcode.itlens.owner.application.mappers.OwnerMapper;
import com.youcode.itlens.owner.application.services.OwnerServiceImpl;
import com.youcode.itlens.owner.domain.Owner;
import com.youcode.itlens.owner.domain.OwnerRepository;
import com.youcode.itlens.survey.application.dtos.PagedResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    private Owner owner;
    private OwnerResponseDTO ownerResponseDTO;
    private OwnerRequestDTO ownerRequestDTO;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setName("Test Owner");

        ownerResponseDTO = new OwnerResponseDTO(1L, "Test Owner");
        ownerRequestDTO = new OwnerRequestDTO("Test Owner");
    }

    @Nested
    @DisplayName("getAll()")
    class GetAllTests {
        @Test
        @DisplayName("should return paginated list of owners")
        void shouldReturnPaginatedListOfOwners() {
            Pageable pageable = PageRequest.of(0, 10);
            Page<Owner> ownerPage = new PageImpl<>(List.of(owner), pageable, 1);

            when(ownerRepository.findAll(pageable)).thenReturn(ownerPage);
            when(ownerMapper.toDto(owner)).thenReturn(ownerResponseDTO);

            PagedResponse<OwnerResponseDTO> response = ownerService.getAll(pageable);

            assertEquals(1, response.content().size());
            assertEquals("Test Owner", response.content().get(0).name());
            verify(ownerRepository, times(1)).findAll(pageable);
            verify(ownerMapper, times(1)).toDto(owner);
        }
    }

    @Nested
    @DisplayName("getById()")
    class GetByIdTests {
        @Test
        @DisplayName("should return owner when found")
        void shouldReturnOwnerWhenFound() {
            when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
            when(ownerMapper.toDto(owner)).thenReturn(ownerResponseDTO);

            OwnerResponseDTO response = ownerService.getById(1L);

            assertEquals("Test Owner", response.name());
            verify(ownerRepository, times(1)).findById(1L);
            verify(ownerMapper, times(1)).toDto(owner);
        }

        @Test
        @DisplayName("should throw EntityNotFoundException when owner not found")
        void shouldThrowEntityNotFoundExceptionWhenOwnerNotFound() {
            when(ownerRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> ownerService.getById(1L));
            verify(ownerRepository, times(1)).findById(1L);
        }
    }

    @Nested
    @DisplayName("save()")
    class SaveTests {
        @Test
        @DisplayName("should save and return new owner")
        void shouldSaveAndReturnNewOwner() {
            when(ownerMapper.toEntity(ownerRequestDTO)).thenReturn(owner);
            when(ownerRepository.save(owner)).thenReturn(owner);
            when(ownerMapper.toDto(owner)).thenReturn(ownerResponseDTO);

            OwnerResponseDTO response = ownerService.save(ownerRequestDTO);

            assertEquals("Test Owner", response.name());
            verify(ownerMapper, times(1)).toEntity(ownerRequestDTO);
            verify(ownerRepository, times(1)).save(owner);
            verify(ownerMapper, times(1)).toDto(owner);
        }
    }

//    @Nested
//    @DisplayName("update()")
//    class UpdateTests {
//        @Test
//        @DisplayName("should update and return owner")
//        void shouldUpdateAndReturnOwner() {
//            when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));
//            when(ownerRepository.save(owner)).thenReturn(owner);
//            when(ownerMapper.toDto(owner)).thenReturn(ownerResponseDTO);
//
//            ownerRequestDTO = new OwnerRequestDTO("Updated Owner");
//            owner.setName("Updated Owner");
//
//            OwnerResponseDTO response = ownerService.update(1L, ownerRequestDTO);
//
//            assertEquals("Updated Owner", response.name());
//            verify(ownerRepository, times(1)).findById(1L);
//            verify(ownerRepository, times(1)).save(owner);
//            verify(ownerMapper, times(1)).toDto(owner);
//        }
//
//        @Test
//        @DisplayName("should throw EntityNotFoundException when owner not found for update")
//        void shouldThrowEntityNotFoundExceptionWhenOwnerNotFoundForUpdate() {
//            when(ownerRepository.findById(1L)).thenReturn(Optional.empty());
//
//            assertThrows(EntityNotFoundException.class, () -> ownerService.update(1L, ownerRequestDTO));
//            verify(ownerRepository, times(1)).findById(1L);
//        }
//    }

    @Nested
    @DisplayName("delete()")
    class DeleteTests {
        @Test
        @DisplayName("should delete owner when exists")
        void shouldDeleteOwnerWhenExists() {
            when(ownerRepository.existsById(1L)).thenReturn(true);

            ownerService.delete(1L);

            verify(ownerRepository, times(1)).existsById(1L);
            verify(ownerRepository, times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("should throw EntityNotFoundException when owner not found for delete")
        void shouldThrowEntityNotFoundExceptionWhenOwnerNotFoundForDelete() {
            when(ownerRepository.existsById(1L)).thenReturn(false);

            assertThrows(EntityNotFoundException.class, () -> ownerService.delete(1L));
            verify(ownerRepository, times(1)).existsById(1L);
        }
    }
}
