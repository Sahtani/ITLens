package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.survey.application.dtos.Answer.AnswerDTO;
import com.youcode.itlens.survey.application.dtos.Participate.ResponseDTO;
import com.youcode.itlens.survey.application.dtos.Participate.SurveyParticipationRequestDTO;
import com.youcode.itlens.survey.application.services.SurveyParticipationService;
import com.youcode.itlens.survey.domain.entities.Answer;
import com.youcode.itlens.survey.domain.entities.Question;
import com.youcode.itlens.survey.domain.repository.AnswerRepository;
import com.youcode.itlens.survey.domain.repository.QuestionRepository;
import com.youcode.itlens.survey.domain.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private SurveyRepository surveyRepository;

    /**
     * Handles survey participation by processing each response and saving it to the database.
     *
     * @param request The survey participation request.
     */
    public void handleSurveyParticipation(SurveyParticipationRequestDTO request) {
        for (ResponseDTO response : request.responses()) {
            if (response.answerId() != null) {
                // Handle single answer
                handleSingleAnswer(response.questionId(), response.answerId());
            } else if (response.answers() != null) {
                // Handle multiple answers
                handleMultipleAnswers(response.questionId(), response.answers());
            }
        }
    }

    /**
     * Processes and saves a single answer (for single-choice questions).
     *
     * @param questionId The question ID.
     * @param answerId The answer ID.
     */
    private void handleSingleAnswer(Long questionId, Long answerId) {
        // Retrieve the question and answer from the database
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + questionId));

        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("Answer not found with ID: " + answerId));

        // Increment the selection count for the answer
        incrementSelectionCount(question, answer);

        // Save the answer to the database
    //    saveSingleAnswer(questionId, answerId);
    }

    /**
     * Processes and saves multiple answers (for multi-choice questions).
     *
     * @param questionId The question ID.
     * @param answerIds The list of answer IDs.
     */
    private void handleMultipleAnswers(Long questionId, List<AnswerDTO> answerIds) {
        // Retrieve the question from the database
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with ID: " + questionId));

        // Retrieve all answers from the database
        List<Answer> answers = answerRepository.findAllById(answerIds);

        if (answers.size() != answerIds.size()) {
            throw new EntityNotFoundException("Some answers not found: " + answerIds);
        }

        // Increment the selection count for each answer
        answers.forEach(answer -> incrementSelectionCount(question, answer));

        // Save the multiple answers to the database
      //  saveMultipleAnswers(questionId, answerIds);
    }

    /**
     * Increments the selection count for a given answer.
     *
     * @param question The question associated with the answer.
     * @param answer The answer whose selection count is to be incremented.
     */
    private void incrementSelectionCount(Question question, Answer answer) {
        answer.incrementSelectionCount();  // Assuming Answer entity has this method
    //    answerRepository.save(answer);  // Save the updated answer to the database
    }

    /**
     * Saves a single answer to the database.
     *
     * @param questionId The question ID.
     * @param answerId The answer ID.
     */
    private void saveSingleAnswer(Long questionId, Long answerId) {
        // Save the single answer response to the database
     //   surveyRepository.saveSingleAnswer(questionId, answerId);
    }

    /**
     * Saves multiple answers to the database.
     *
     * @param questionId The question ID.
     * @param answerIds The list of answer IDs.
     */
//    private void saveMultipleAnswers(Long questionId, List<Long> answerIds) {
//        // Save the multiple answer responses to the database
//        surveyRepository.saveMultipleAnswers(questionId, answerIds);
//    }
}
