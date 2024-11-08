package com.youcode.itlens.survey.application.dtos.Chapter;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.survey.domain.entities.Chapter;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChapterRequestDTO(
        @NotBlank String title,

        @NotNull
        @Existe(entityClass = SurveyEdition.class, fieldName = "id", message = "No survey edition with this ID")
        Long surveyEditionId,

        @Existe(entityClass = Chapter.class, fieldName = "id", message = "No parent chapter with this ID")
        Long parentChapterId
) {}
