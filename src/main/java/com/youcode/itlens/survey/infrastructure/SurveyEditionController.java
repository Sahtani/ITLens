package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionRequestDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionResponseDTO;
import com.youcode.itlens.survey.application.services.SurveyEditionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/surveyeditions")
@RequiredArgsConstructor
class SurveyEditionController {
    private final SurveyEditionService service;

    @GetMapping
    public ResponseEntity<List<SurveyEditionResponseDTO>> findAll() {
        List<SurveyEditionResponseDTO> surveyEditions = service.getAll();
        return ResponseEntity.ok(surveyEditions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDTO> findById(@PathVariable Long id) {
        SurveyEditionResponseDTO surveyEdition = service.getById(id);
        return ResponseEntity.ok(surveyEdition);
    }

    @PostMapping
    public ResponseEntity<SurveyEditionResponseDTO> create(@RequestBody @Valid SurveyEditionRequestDTO dto) {
        SurveyEditionResponseDTO surveyEdition = service.save(dto);
        return new ResponseEntity<>(surveyEdition, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyEditionResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SurveyEditionRequestDTO dto) {
        SurveyEditionResponseDTO surveyEdition = service.update(id, dto);
        return ResponseEntity.ok(surveyEdition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}