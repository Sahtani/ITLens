package com.youcode.itlens.survey.application.dtos.Answer;

import com.youcode.itlens.survey.application.dtos.Question.QuestionEmbeddableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerResponseDTO(@NotNull Long id,
                                @NotBlank String text,
                                @NotNull QuestionEmbeddableDTO question
) {
}