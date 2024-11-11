package com.youcode.itlens.survey.application.dtos.Participate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

// List that may contain SimpleResponseDTO or MultipleResponseDTO
public record SurveyParticipationRequestDTO(@NotNull List<ResponseDTO> responses

) {
}
