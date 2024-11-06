package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.common.services.GenericService;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.domain.entities.Survey;

public interface SurveyService extends GenericService<Survey, Long , SurveyRequestDTO, SurveyResponseDTO> {
}
