package com.youcode.itlens.survey.application.dtos.Chapter;

import com.youcode.itlens.survey.application.dtos.Question.QuestionDTO;
import com.youcode.itlens.survey.application.dtos.Question.QuestionEmbeddableDTO;
import com.youcode.itlens.survey.application.dtos.Question.QuestionResponseDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionEmbeddableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ChapterResponseDTO(
        @NotNull Long id,
        @NotBlank String title,
        @NotNull List<ChapterEmbeddableDTO> subChapters
        ) {
}
