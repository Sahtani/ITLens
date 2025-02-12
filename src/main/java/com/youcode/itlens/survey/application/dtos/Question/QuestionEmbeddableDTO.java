package com.youcode.itlens.survey.application.dtos.Question;

import com.youcode.itlens.survey.application.dtos.Answer.AnswerEmbeddableDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterEmbeddableDTO;
import com.youcode.itlens.survey.domain.enums.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionEmbeddableDTO(@NotNull Long id,
                                    @NotBlank String text,
                                    @NotNull QuestionType type,
                                    @NotNull List<AnswerEmbeddableDTO> answers
) {
}