package com.youcode.itlens.survey.application.services.Implementations;


import com.youcode.itlens.survey.application.dtos.Answer.AnswerDTO;
import com.youcode.itlens.survey.application.dtos.Participate.ResponseDTO;
import com.youcode.itlens.survey.application.dtos.Participate.SurveyParticipationRequestDTO;
import com.youcode.itlens.survey.application.services.SurveyParticipationService;
import com.youcode.itlens.survey.domain.entities.Answer;
import com.youcode.itlens.survey.domain.entities.Question;
import com.youcode.itlens.survey.domain.enums.QuestionType;
import com.youcode.itlens.survey.domain.repository.AnswerRepository;
import com.youcode.itlens.survey.domain.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
@Transactional
public class SurveyParticipationServiceImpl implements SurveyParticipationService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    @Override
    public void participate(Long surveyId, SurveyParticipationRequestDTO participationDTO) {
        for (ResponseDTO responseDTO : participationDTO.responses()) {
            Question question = questionRepository.findById(responseDTO.questionId())
                    .orElseThrow(() -> new IllegalArgumentException("Question not found"));

            if (question.getChapter() == null) {
                throw new IllegalArgumentException("Question ID = " + question.getId() + " has no associated subject.");
            }

            if (!question.getChapter().getSurveyEdition().getSurvey().getId().equals(surveyId)) {
                throw new IllegalArgumentException("Question " + responseDTO.questionId() + " does not belong to survey " + surveyId);
            }

            // Handle Single Choice Questions
            if (question.getType() == QuestionType.SINGLE_CHOICE) {
                if (responseDTO.answerId() == null || responseDTO.answers() != null) {
                    throw new IllegalArgumentException("Only one answer is required for single-choice question ID = " + question.getId());
                }

                Answer answer = answerRepository.findById(responseDTO.answerId())
                        .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

                if (!answer.getQuestion().getId().equals(question.getId())) {
                    throw new IllegalArgumentException("Answer ID = " + responseDTO.answerId() + " does not belong to question ID = " + question.getId());
                }

                answer.incrementSelectionCount();
                answerRepository.save(answer);

                question.incrementAnswerCount();
                questionRepository.save(question);

                // Handle Multiple Choice Questions
            } else if (question.getType() == QuestionType.MULTIPLE_CHOICE) {
                if (responseDTO.answers() == null || responseDTO.answers().isEmpty() || responseDTO.answerId() != null) {
                    throw new IllegalArgumentException("Multiple answers are required for multiple-choice question ID = " + question.getId());
                }

                for (AnswerDTO answerDTO : responseDTO.answers()) {
                    Answer answer = answerRepository.findById(answerDTO.answerId())
                            .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

                    if (!answer.getQuestion().getId().equals(question.getId())) {
                        throw new IllegalArgumentException("Answer ID = " + answerDTO.answerId() + " does not belong to question ID = " + question.getId());
                    }

                    answer.incrementSelectionCount();
                    answerRepository.save(answer);
                }

                question.incrementAnswerCount();
                questionRepository.save(question);

            }
        }}}