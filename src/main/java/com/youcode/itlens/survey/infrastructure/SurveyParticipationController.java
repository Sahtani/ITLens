package com.youcode.itlens.survey.application.controllers;

import com.youcode.itlens.survey.application.dtos.Participate.SurveyParticipationRequest;
import com.youcode.itlens.survey.application.services.SurveyParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyParticipationController {

    private final SurveyParticipationService surveyParticipationService;

    @PostMapping("/{surveyId}/participate")
    public ResponseEntity<Void> participate(@PathVariable Long surveyId, @RequestBody @Valid SurveyParticipationRequest dto) {

        if (dto.simpleAnswerDTO() != null) {
            surveyParticipationService.participateWithSingleAnswer(surveyId, List.of(dto.simpleAnswerDTO()));
        } else if (dto.multipleAnswerDTO() != null) {
            surveyParticipationService.participateWithMultipleAnswers(surveyId, List.of(dto.multipleAnswerDTO()));
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

}
