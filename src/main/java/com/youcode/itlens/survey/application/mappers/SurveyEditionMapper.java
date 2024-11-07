package com.youcode.itlens.survey.application.mappers;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionRequestDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionResponseDTO;
import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SurveyEditionMapper extends GenericMapper<SurveyEdition, SurveyEditionRequestDTO, SurveyEditionResponseDTO> {
}
