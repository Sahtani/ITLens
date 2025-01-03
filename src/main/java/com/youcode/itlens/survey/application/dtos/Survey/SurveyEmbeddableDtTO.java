package com.youcode.itlens.survey.application.dtos.Survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyEmbeddableDtTO(@NotNull Long id,
                                   @NotBlank String title,
                                   @NotBlank String description) {
}
