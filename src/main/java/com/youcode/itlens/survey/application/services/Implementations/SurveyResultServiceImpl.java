package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResultDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.SubChapterResultDTO;
import com.youcode.itlens.survey.application.dtos.Participate.SurveyResultDTO;
import com.youcode.itlens.survey.application.services.SurveyResultService;
import com.youcode.itlens.survey.domain.entities.*;
import com.youcode.itlens.survey.domain.repository.SurveyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class SurveyResultServiceImpl implements SurveyResultService {

    private final SurveyRepository surveyRepository;

    @Override
    public SurveyResultDTO getSurveyResults(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        List<ChapterResultDTO> chapters = new ArrayList<>();
        for (SurveyEdition surveyEdition : survey.getSurveyEditions()) {
            for (Chapter chapter : surveyEdition.getChapters()) {
                ChapterResultDTO chapterResult = new ChapterResultDTO(
                        chapter.getTitle(), buildSubChapters(chapter)
                );
                chapters.add(chapterResult);
            }
        }
        return new SurveyResultDTO(survey.getTitle(), chapters);
    }

    // Helper method to build sub-chapters from a subject
    private List<SubChapterResultDTO> buildSubChapters(Chapter chapter) {
        List<SubChapterResultDTO> subChapters = new ArrayList<>();
        for (Chapter subChapter : chapter.getSubChapters()) {
            // Loop through the questions of the sub-subject
            for (Question question : subChapter.getQuestions()) {
                subChapters.add(buildSubChapterResult(subChapter, question));
            }
        }

        return subChapters;
    }

    // Helper method to build SubChapterResultDTO from a question and sub-subject
    private SubChapterResultDTO buildSubChapterResult(Chapter subChapter, Question question) {
        Map<String, Integer> answerCounts = new HashMap<>();
        for (Answer answer : question.getAnswers()) {
            answerCounts.put(answer.getText(), answer.getSelectionCount());
        }
        return new SubChapterResultDTO(subChapter.getTitle(), question.getText(), answerCounts, question.getAnswerCount());
    }
}
