package com.youcode.itlens.survey.application.mappers;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.survey.application.dtos.Question.QuestionRequestDTO;
import com.youcode.itlens.survey.application.dtos.Question.QuestionResponseDTO;
import com.youcode.itlens.survey.domain.entities.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper extends GenericMapper<Question, QuestionRequestDTO, QuestionResponseDTO> {
}
