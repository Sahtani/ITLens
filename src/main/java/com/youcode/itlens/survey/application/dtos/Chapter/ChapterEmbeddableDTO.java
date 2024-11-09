package com.youcode.itlens.survey.application.dtos.Chapter;

import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionEmbeddableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChapterEmbeddableDTO (@NotNull Long id,
                                   @NotBlank String title,
                                   @NotNull SurveyEditionEmbeddableDTO surveyEdition
) {
}