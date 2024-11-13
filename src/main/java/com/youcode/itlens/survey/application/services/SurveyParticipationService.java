package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.survey.application.dtos.Participate.SurveyParticipationRequestDTO;

import java.util.List;

public interface SurveyParticipationService {

    public void participate(Long surveyId, SurveyParticipationRequestDTO participationDTO);
}
