package com.youcode.itlens.common.services;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.common.services.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
public abstract class GenericCrudServiceImpl<T, RequestDTO, ResponseDTO, ID> implements CrudService<RequestDTO, ResponseDTO, ID> {

    private final JpaRepository<T, ID> repository;
    private final GenericMapper<T, RequestDTO, ResponseDTO> mapper;

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
}
