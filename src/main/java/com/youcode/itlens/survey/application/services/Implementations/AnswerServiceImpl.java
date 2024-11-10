package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.common.services.GenericCrudServiceImpl;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerRequestDTO;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerResponseDTO;
import com.youcode.itlens.survey.application.services.AnswerService;
import com.youcode.itlens.survey.domain.entities.Answer;

public class AnswerServiceImpl extends GenericCrudServiceImpl<Answer, AnswerRequestDTO, AnswerResponseDTO, Long> implements AnswerService {
}
