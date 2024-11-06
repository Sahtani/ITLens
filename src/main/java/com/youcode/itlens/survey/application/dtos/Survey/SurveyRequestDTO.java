package com.youcode.itlens.survey.application.dtos.Survey;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.owner.domain.Owner;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyRequestDTO(

        @NotBlank String title, @NotBlank String description,
        @NotNull @Existe(entityClass = Owner.class, fieldName = "id", message = "The specified owner does not exist.") Long ownerId) {
}
