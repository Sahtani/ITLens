package com.youcode.itlens.survey.application.dtos.Participate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// List that may contain SimpleResponseDTO or MultipleResponseDTO
public record SurveyParticipationRequest(@NotNull SimpleAnswerDTO simpleAnswerDTO,
                                         @NotBlank MultipleAnswerDTO multipleAnswerDTO

) {
}
