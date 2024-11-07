package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.common.services.GenericService;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionRequestDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionResponseDTO;
import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;

public interface SurveyEditionService extends GenericService<SurveyEdition, Long , SurveyEditionRequestDTO, SurveyEditionResponseDTO> {
}
