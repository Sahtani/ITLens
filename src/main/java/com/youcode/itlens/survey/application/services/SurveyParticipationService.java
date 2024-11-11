package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.survey.application.dtos.Participate.MultipleAnswerDTO;
import com.youcode.itlens.survey.application.dtos.Participate.SimpleAnswerDTO;

import java.util.List;

public interface SurveyParticipationService {

    public void participateWithSingleAnswer(Long surveyId, List<SimpleAnswerDTO> responses);
    public void participateWithMultipleAnswers(Long surveyId, List<MultipleAnswerDTO> responses);
}
