package com.youcode.itlens.survey.application.dtos.Participate;

import java.util.List;

public record SurveyParticipationRequestDTO(
        List<ResponseDTO> responses
) {
}
