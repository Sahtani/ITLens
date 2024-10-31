package com.youcode.itlens.owner.application.services;

import com.youcode.itlens.owner.application.dtos.OwnerRequestDTO;
import com.youcode.itlens.owner.application.dtos.OwnerResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Validated
public class OwnerServiceImpl implements OwnerService {
    @Override
    public List<OwnerResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public Optional<OwnerResponseDTO> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public OwnerResponseDTO save(OwnerRequestDTO ownerRequestDTO) {
        return null;
    }

    @Override
    public OwnerResponseDTO update(Long id, OwnerRequestDTO ownerRequestDTO) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
