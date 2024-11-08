package com.youcode.itlens.common.services;

import java.util.List;

public interface CrudService<RequestDTO, ResponseDTO, ID> {
    ResponseDTO save(RequestDTO requestDto);

    ResponseDTO findById(ID id);

    List<ResponseDTO> findAll();

    void deleteById(ID id);
}
