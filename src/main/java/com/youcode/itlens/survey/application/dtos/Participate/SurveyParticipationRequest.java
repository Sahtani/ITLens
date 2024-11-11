package com.youcode.itlens.survey.application.dtos.Participate;

import jakarta.validation.constraints.NotNull;

import java.util.List;
// List that may contain SimpleResponseDTO or MultipleResponseDTO
public record SurveyParticipationRequest(@NotNull List<Object> responses

) {
}
