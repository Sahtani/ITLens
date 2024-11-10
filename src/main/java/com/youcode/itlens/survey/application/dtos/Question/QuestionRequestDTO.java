package com.youcode.itlens.survey.application.dtos.Question;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.survey.application.dtos.Answer.AnswerRequestDTO;
import com.youcode.itlens.survey.domain.entities.Chapter;
import com.youcode.itlens.survey.domain.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionRequestDTO(@NotBlank String text,
                                 @NotNull @Existe(entityClass = Chapter.class, fieldName = "id", message = "no chapter exist with this id")
                                 Long chapterId,
                                 @NotNull QuestionType questionType,
                                 @NotNull List<AnswerRequestDTO> answerRequestDTOS
) {
}