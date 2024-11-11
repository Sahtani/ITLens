package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.survey.application.dtos.Participate.MultipleAnswerDTO;
import com.youcode.itlens.survey.application.dtos.Participate.SimpleAnswerDTO;
import com.youcode.itlens.survey.application.services.SurveyParticipationService;
import com.youcode.itlens.survey.domain.entities.Answer;
import com.youcode.itlens.survey.domain.entities.Question;
import com.youcode.itlens.survey.domain.repository.AnswerRepository;
import com.youcode.itlens.survey.domain.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void participateWithSingleAnswer(Long surveyId, List<SimpleAnswerDTO> answers) {
        for (SimpleAnswerDTO answerDTO : answers) {
            Question question = questionRepository.findById(answerDTO.questionId()).orElseThrow(() -> new IllegalArgumentException("Question not found for ID " + answerDTO.questionId()));
            String answerId = answerDTO.answerId();

            // If the answer is a combination of IDs (e.g., "25-34")
            if (answerId.contains("-")) {
                String[] answerIds = answerId.split("-");
                for (String id : answerIds) {
                    Long parsedAnswerId = Long.parseLong(id);
                    processAnswer(parsedAnswerId, question);
                }
            } else {
                // Single answer ID
                Long parsedAnswerId = Long.parseLong(answerId);
                processAnswer(parsedAnswerId, question);
            }
        }
    }

    @Override
    // Multiple response processing
    public void participateWithMultipleAnswers(Long surveyId, List<MultipleAnswerDTO> answers) {
        for (MultipleAnswerDTO answerDTO : answers) {
            Question question = questionRepository.findById(answerDTO.questionId()).orElseThrow(() -> new IllegalArgumentException("Question not found for ID " + answerDTO.questionId()));
            List<Long> answerIds = answerDTO.answers();
            for (Long answerId : answerIds) {
                processAnswer(answerId, question);
            }
        }
    }

    private void processAnswer(Long answerId, Question question) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("Answer not found for ID " + answerId));

        validateAnswerBelongsToQuestion(answer, question);
        answer.incrementSelectionCount();

        // Increment answer count for the question
        question.incrementAnswerCount();
    }

    private void validateAnswerBelongsToQuestion(Answer answer, Question question) {
        if (!answer.getQuestion().getId().equals(question.getId())) {
            throw new IllegalArgumentException("Answer with ID " + answer.getId() + " does not belong to question " + question.getId());
        }
    }
}
