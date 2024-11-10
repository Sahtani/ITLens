package com.youcode.itlens.survey.application.dtos.Answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerEmbeddableDTO(@NotNull Long id,
                                  @NotBlank String text,
                                  @NotNull int selectionCount,
                                  @NotNull double percentage
) {
}