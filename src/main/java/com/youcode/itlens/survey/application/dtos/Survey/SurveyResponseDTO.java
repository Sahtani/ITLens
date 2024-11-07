package com.youcode.itlens.survey.application.dtos.Survey;

import com.youcode.itlens.owner.application.dtos.OwnerEmbeddableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyResponseDTO(@NotNull Long id,
                                @NotBlank String title,
                                @NotBlank String description,
                                @NotNull OwnerEmbeddableDTO owner) {
}
