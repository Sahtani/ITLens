package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.application.services.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/surveys")
@RequiredArgsConstructor
class SurveyController {
    private final SurveyService service;

    @GetMapping
    public ResponseEntity<List<SurveyResponseDTO>> findAll() {
        List<SurveyResponseDTO> surveys = service.getAll();
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDTO> findById(@PathVariable Long id) {
        SurveyResponseDTO survey = service.getById(id);
        return ResponseEntity.ok(survey);
    }

    @PostMapping
    public ResponseEntity<SurveyResponseDTO> create(@RequestBody @Valid SurveyRequestDTO dto) {
        SurveyResponseDTO survey = service.save(dto);
        return new ResponseEntity<>(survey, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SurveyRequestDTO dto) {
        SurveyResponseDTO survey = service.update(id, dto);
        return ResponseEntity.ok(survey);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}