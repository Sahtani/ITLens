package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.common.services.CrudService;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerRequestDTO;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerResponseDTO;

public interface AnswerService extends CrudService<AnswerRequestDTO, AnswerResponseDTO,Long> {
}
