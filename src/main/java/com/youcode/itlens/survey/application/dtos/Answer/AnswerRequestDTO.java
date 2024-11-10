package com.youcode.itlens.survey.application.dtos.Answer;

import jakarta.validation.constraints.NotBlank;

public record AnswerRequestDTO(@NotBlank String text) {
}