package com.youcode.itlens.survey.application.dtos.Participate;

import jakarta.validation.constraints.NotNull;

import java.util.List;
// Answer ID list
public record MultipleAnswerDTO(@NotNull Long questionId,
        @NotNull List<Long> answerIds

 ) {
}
