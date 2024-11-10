package com.youcode.itlens.survey.application.mappers;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerRequestDTO;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerResponseDTO;
import com.youcode.itlens.survey.domain.entities.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper extends GenericMapper<Answer, AnswerRequestDTO, AnswerResponseDTO> {
}
