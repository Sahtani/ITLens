package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.common.controllers.GenericController;
import com.youcode.itlens.survey.application.dtos.Question.QuestionRequestDTO;
import com.youcode.itlens.survey.application.dtos.Question.QuestionResponseDTO;
import com.youcode.itlens.survey.application.services.QuestionService;
import com.youcode.itlens.survey.domain.entities.Question;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController extends GenericController<QuestionRequestDTO, QuestionResponseDTO,Long> {
    public QuestionController(QuestionService questionService) {
        super(questionService);
    }
}
