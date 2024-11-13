package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.survey.application.dtos.Participate.SurveyResultDTO;

public interface SurveyResultService {

    SurveyResultDTO getSurveyResults(Long surveyId);
}
