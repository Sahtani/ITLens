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
import com.youcode.itlens.survey.domain.repository.ChapterRepository;
import com.youcode.itlens.survey.domain.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

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
        if (requestDTO.chapterId() != null) {
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
        } else {
            throw new IllegalArgumentException("Chapter ID must be provided.");
        }
    }


}
