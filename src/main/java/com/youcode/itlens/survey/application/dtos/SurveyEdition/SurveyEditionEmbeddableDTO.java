package com.youcode.itlens.survey.application.dtos.SurveyEdition;

import com.youcode.itlens.survey.application.dtos.Chapter.ChapterEmbeddableDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResponseDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyEmbeddableDtTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

public record SurveyEditionEmbeddableDTO(@NotNull Long id,
                                         @NotNull LocalDateTime creationDate,
                                         @NotNull LocalDateTime startDate,
                                         @NotNull Year year,
                                         @NotNull List<ChapterResponseDTO> chapters

) {
}