package com.youcode.itlens.common.controllers;

import com.youcode.itlens.common.services.CrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<T, RequestDTO, ResponseDTO, ID> {

    protected final CrudService<RequestDTO, ResponseDTO, ID> service;

    public GenericController(CrudService<RequestDTO, ResponseDTO, ID> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody RequestDTO requestDto) {
        ResponseDTO responseDto = service.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable ID id) {
        ResponseDTO responseDto = service.findById(id);
        return responseDto != null ? ResponseEntity.ok(responseDto) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> getAll() {
        List<ResponseDTO> responseList = service.findAll();
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable ID id, @RequestBody @Valid RequestDTO requestDto) {
        ResponseDTO updatedDto = service.update(id, requestDto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
