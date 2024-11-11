package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.common.services.GenericCrudServiceImpl;
import com.youcode.itlens.survey.application.dtos.Question.QuestionRequestDTO;
import com.youcode.itlens.survey.application.dtos.Question.QuestionResponseDTO;
import com.youcode.itlens.survey.application.mappers.AnswerMapper;
import com.youcode.itlens.survey.application.mappers.QuestionMapper;
import com.youcode.itlens.survey.application.services.QuestionService;
import com.youcode.itlens.survey.domain.entities.Answer;
import com.youcode.itlens.survey.domain.entities.Chapter;
import com.youcode.itlens.survey.domain.entities.Question;
import com.youcode.itlens.survey.domain.exception.ChapterHasSubChaptersException;
import com.youcode.itlens.survey.domain.repository.ChapterRepository;
import com.youcode.itlens.survey.domain.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@Transactional
public class QuestionServiceImpl extends GenericCrudServiceImpl<Question, QuestionRequestDTO, QuestionResponseDTO, Long> implements QuestionService {


    //todo :override save and update methods

    private final QuestionRepository questionRepository;
    private final ChapterRepository chapterRepository;
    private final QuestionMapper mapper;
    private final AnswerMapper answerMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, ChapterRepository chapterRepository, QuestionMapper mapper, AnswerMapper answerMapper) {
        super(questionRepository, mapper);
        this.questionRepository = questionRepository;
        this.chapterRepository = chapterRepository;
        this.mapper = mapper;
        this.answerMapper = answerMapper;
    }

    @Override
    public QuestionResponseDTO save(QuestionRequestDTO requestDTO) {

        ensureIsLeafChapter(requestDTO.chapterId());
        Chapter chapter = chapterRepository.findById(requestDTO.chapterId()).orElseThrow(() -> new EntityNotFoundException("Chapter with ID " + requestDTO.chapterId() + " not found."));
            Question question = mapper.toEntity(requestDTO);

            // set the chapter
            question.setChapter(chapter);

            //  add each response to the question if they exist in the request DTO
            if (requestDTO.answerRequestDTOS() != null) {
                List<Answer> answers = requestDTO.answerRequestDTOS().stream().map(responseDTO -> {
                    Answer answer = answerMapper.toEntity(responseDTO);
                    answer.setQuestion(question); // Set the question in each response
                    return answer;
                }).toList();
                question.setAnswers(answers);
            }
            Question savedQuestion = questionRepository.save(question);

            return mapper.toDto(savedQuestion);

    }

    @Override
    public QuestionResponseDTO update(Long id, QuestionRequestDTO requestDTO) {

        ensureIsLeafChapter(requestDTO.chapterId());

        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question with ID " + id + " not found."));

        if (requestDTO.chapterId() != null) {
            Chapter chapter = chapterRepository.findById(requestDTO.chapterId())
                    .orElseThrow(() -> new EntityNotFoundException("Chapter with ID " + requestDTO.chapterId() + " not found."));
            existingQuestion.setChapter(chapter);
        }

        existingQuestion.setText(requestDTO.text());
        existingQuestion.setType(requestDTO.questionType());

        // Update answers if provided
        if (requestDTO.answerRequestDTOS() != null) {
            List<Answer> updatedAnswers = requestDTO.answerRequestDTOS().stream()
                    .map(answerDTO -> {
                        Answer answer = answerMapper.toEntity(answerDTO);
                        answer.setQuestion(existingQuestion);
                        return answer;
                    })
                    .collect(Collectors.toList());
            existingQuestion.setAnswers(updatedAnswers);
        }
        Question updatedQuestion = questionRepository.save(existingQuestion);
        return mapper.toDto(updatedQuestion);
    }

    // Ensure the chapter does not have any subchapters
    private void ensureIsLeafChapter(Long chapterId) {
        if (chapterRepository.hasSubChapters(chapterId)) {
            throw new ChapterHasSubChaptersException(chapterId);
        }
    }


}
