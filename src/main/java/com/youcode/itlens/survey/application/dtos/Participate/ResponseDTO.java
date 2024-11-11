package com.youcode.itlens.survey.application.dtos.Participate;

import com.youcode.itlens.survey.application.dtos.Answer.AnswerDTO;

import java.util.List;

public record ResponseDTO(Long questionId,
                          Long answerId,
                          List<AnswerDTO> answers) {
}
