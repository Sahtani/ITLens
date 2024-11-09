package com.youcode.itlens.common.services;

import com.youcode.itlens.common.mappers.GenericMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Validated
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class GenericCrudServiceImpl<T, RequestDTO, ResponseDTO, ID> implements CrudService<RequestDTO, ResponseDTO, ID> {

    protected JpaRepository<T, ID> repository;
    protected GenericMapper<T, RequestDTO, ResponseDTO> mapper;





    @Override
    public ResponseDTO save(RequestDTO requestDto) {
        T entity = mapper.toEntity(requestDto);
        T savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public ResponseDTO findById(ID id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<ResponseDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public ResponseDTO update(ID id, RequestDTO requestDto) {
        Optional<T> existingEntity = repository.findById(id);
        if (existingEntity.isPresent()) {
            T entity = existingEntity.get();
            mapper.toEntity(requestDto  );

            T updatedEntity = repository.save(entity);

            return mapper.toDto(updatedEntity);
        } else {
            throw new EntityNotFoundException("Entity with ID " + id + " not found.");
        }
    }
}