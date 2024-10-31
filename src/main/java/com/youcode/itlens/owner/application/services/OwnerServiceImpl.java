package com.youcode.itlens.owner.application.services;

import com.youcode.itlens.owner.application.mappers.OwnerRequestDTO;
import com.youcode.itlens.owner.application.mappers.OwnerResponseDTO;

import java.util.List;
import java.util.Optional;

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
