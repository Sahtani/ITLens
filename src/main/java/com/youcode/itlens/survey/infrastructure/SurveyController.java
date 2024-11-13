package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.survey.application.dtos.PagedResponse;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.application.services.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/surveys")
@RequiredArgsConstructor
class SurveyController {
    private final SurveyService service;

    @GetMapping
    public ResponseEntity<PagedResponse<SurveyResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PagedResponse<SurveyResponseDTO> surveys = service.getAll(pageable);
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