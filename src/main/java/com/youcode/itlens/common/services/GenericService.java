package com.youcode.itlens.common.services;

import com.youcode.itlens.survey.application.dtos.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericService<T, ID ,RequestDto, ResponseDto> {
    PagedResponse<ResponseDto> getAll(Pageable pageable);

    ResponseDto getById(ID id);
    ResponseDto save(RequestDto requestDto);
    ResponseDto update(Long id,RequestDto requestDto);
    void delete(ID id);

}