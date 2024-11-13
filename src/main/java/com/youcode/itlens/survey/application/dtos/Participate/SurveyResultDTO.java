package com.youcode.itlens.survey.application.dtos.Participate;

import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResultDTO;

import java.util.List;

public record SurveyResultDTO(String surveyTitle,
                              List<ChapterResultDTO> chapters) {
}
