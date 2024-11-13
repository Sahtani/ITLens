package com.youcode.itlens.common.services;

import com.youcode.itlens.survey.application.dtos.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<RequestDTO, ResponseDTO, ID> {
    ResponseDTO save(RequestDTO requestDto);

    ResponseDTO update(ID id, RequestDTO requestDto);

    ResponseDTO findById(ID id);

    PagedResponse<ResponseDTO> findAll(Pageable pageable);

    void deleteById(ID id);
}
