package com.youcode.itlens.survey.application.dtos.Participate;

import jakarta.validation.constraints.NotNull;

// It may be a combination of responses, for example “25-34”.
public record SingleAnswerRequestDTO(@NotNull Long questionId,
                              @NotNull String answerId

) {
}
