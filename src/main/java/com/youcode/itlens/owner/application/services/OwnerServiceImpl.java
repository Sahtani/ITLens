package com.youcode.itlens.owner.application.services;

import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import com.youcode.itlens.owner.application.mappers.OwnerMapper;
import com.youcode.itlens.owner.domain.Owner;
import com.youcode.itlens.owner.domain.OwnerRepository;
import com.youcode.itlens.survey.application.dtos.PagedResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    @Override
    public PagedResponse<OwnerResponseDTO> getAll(Pageable pageable) {
        Page<Owner> ownersPage = ownerRepository.findAll(pageable);
        List<OwnerResponseDTO> ownerResponseDTOs = ownersPage.getContent().stream().map(ownerMapper::toDto).toList();

        return new PagedResponse<>(ownerResponseDTOs, ownersPage.getNumber(), ownersPage.getSize(), ownersPage.getTotalElements(), ownersPage.getTotalPages(), ownersPage.isLast());
    }


    @Override
    public OwnerResponseDTO getById(Long id) {
        return ownerRepository.findById(id).map(ownerMapper::toDto).orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + id));
    }

    @Override
    public OwnerResponseDTO save(OwnerRequestDTO ownerRequestDTO) {
        Owner owner = ownerMapper.toEntity(ownerRequestDTO);
        return ownerMapper.toDto(ownerRepository.save(owner));
    }

    @Override
    public OwnerResponseDTO update(Long id, OwnerRequestDTO ownerRequestDTO) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Owner not found with ID: " + id));
        owner.setName(ownerRequestDTO.name());
        return ownerMapper.toDto(ownerRepository.save(owner));
    }

    @Override
    public void delete(Long id) {
        if (!ownerRepository.existsById(id)) {
            throw new EntityNotFoundException("Owner not found with ID: " + id);
        }
        ownerRepository.deleteById(id);

    }
}
