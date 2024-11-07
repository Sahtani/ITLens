package com.youcode.itlens.survey.application.dtos.SurveyEdition;

import com.youcode.itlens.survey.application.dtos.Survey.SurveyEmbeddableDtTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.Year;

public record SurveyEditionResponseDTO(@NotNull Long id,
                                       @NotNull LocalDateTime startDate,
                                       @NotNull LocalDateTime endDate,
                                       @NotNull Year year,
                                       @NotNull SurveyEmbeddableDtTO survey
) {
}