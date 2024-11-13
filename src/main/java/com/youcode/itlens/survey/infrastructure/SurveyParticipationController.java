package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.survey.application.dtos.Participate.SurveyParticipationRequestDTO;
import com.youcode.itlens.survey.application.dtos.Participate.SurveyResultDTO;
import com.youcode.itlens.survey.application.services.SurveyParticipationService;
import com.youcode.itlens.survey.application.services.SurveyResultService;
import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveys")
@RequiredArgsConstructor
public class SurveyParticipationController {

    private final SurveyParticipationService surveyParticipationService;

    private final SurveyResultService surveyResultService;


    @PostMapping("/{id}/participate")
    public ResponseEntity<String> participateSurvey(@Existe(entityClass = Survey.class, fieldName = "id", message = "No survey edition with this ID") @PathVariable("id") Long surveyId,
                                                    @RequestBody @Valid SurveyParticipationRequestDTO dto) {
        surveyParticipationService.participate(surveyId, dto);
        return new ResponseEntity<>("\n" +
                "Participation successfully registered", HttpStatus.OK);
    }
    @GetMapping("/{id}/results")
    public ResponseEntity<SurveyResultDTO> getSurveyResults(@PathVariable("id") Long surveyId) {
        SurveyResultDTO surveyResult = surveyResultService.getSurveyResults(surveyId);
        return new ResponseEntity<>(surveyResult, HttpStatus.OK);
    }
}
