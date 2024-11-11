package com.youcode.itlens.survey.application.dtos.Participate;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerDTO;
import com.youcode.itlens.survey.domain.entities.Chapter;
import com.youcode.itlens.survey.domain.entities.Question;
import jakarta.validation.constraints.NotNull;

import java.util.List;
// Answer ID list
public record MultipleAnswerDTO(@NotNull  @Existe(entityClass = Question.class, fieldName = "id",message = "Question not found for this id ") Long questionId,
        @NotNull List<AnswerDTO> answers

 ) {
}
