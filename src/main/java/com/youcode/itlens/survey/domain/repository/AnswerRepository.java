package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.application.dtos.Answer.AnswerDTO;
import com.youcode.itlens.survey.domain.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
   // List<Answer> findAllById(List<AnswerDTO> answerIds);

    // List<Answer> findAllById(List<AnswerDTO> answerIds);
}
