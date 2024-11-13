package com.youcode.itlens.survey.application.dtos.Chapter;

import java.util.Map;

public record SubChapterResultDTO(String title,
                 String question,
                 Map<String, Integer>answers,
                 int totalAnswers) {
}
