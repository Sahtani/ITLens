package com.youcode.itlens.survey.application.dtos.Survey;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.common.annotations.declarations.Unique;
import com.youcode.itlens.owner.domain.Owner;
import com.youcode.itlens.survey.domain.entities.Survey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyRequestDTO(

        @NotBlank @Unique(fieldName = "title", entityClass = Survey.class, message = "survey title already exists") String title,
        @NotBlank String description,
        @NotNull @Existe(entityClass = Owner.class, fieldName = "id", message = "The specified owner does not exist.") Long ownerId) {
}
