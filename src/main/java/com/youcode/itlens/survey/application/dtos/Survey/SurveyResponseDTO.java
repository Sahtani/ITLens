package com.youcode.itlens.survey.application.dtos.Survey;

import com.youcode.itlens.owner.application.dtos.OwnerEmbeddableDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionEmbeddableDTO;
import com.youcode.itlens.survey.domain.entities.Survey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SurveyResponseDTO(@NotNull Long id,
                                @NotBlank String title,
                                @NotBlank String description,
                                @NotNull OwnerEmbeddableDTO owner,
                                @NotNull List<SurveyEditionEmbeddableDTO> surveyEditions
                                ) {
}
