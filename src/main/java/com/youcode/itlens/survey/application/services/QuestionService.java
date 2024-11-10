package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.common.services.CrudService;
import com.youcode.itlens.survey.application.dtos.Question.QuestionRequestDTO;
import com.youcode.itlens.survey.application.dtos.Question.QuestionResponseDTO;
import com.youcode.itlens.survey.domain.entities.Question;

public interface QuestionService extends CrudService<QuestionRequestDTO, QuestionResponseDTO,Long> {
}
