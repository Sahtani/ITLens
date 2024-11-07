package com.youcode.itlens.survey.application.dtos.SurveyEdition;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.survey.domain.entities.Survey;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Year;

public record SurveyEditionRequestDTO(
        @NotNull
        @FutureOrPresent
        LocalDateTime creationDate,

        @NotNull
        @Future
        LocalDateTime startDate,

        @NotNull
        @FutureOrPresent
        Year year,

        @NotNull
        @Existe(entityClass = Survey.class, fieldName = "id", message = "No survey with this ID")
        Long surveyId
) {}
