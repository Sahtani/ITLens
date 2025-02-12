package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.application.dtos.Answer.AnswerDTO;
import com.youcode.itlens.survey.domain.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
   // List<Answer> findAllById(List<AnswerDTO> answerIds);

    // List<Answer> findAllById(List<AnswerDTO> answerIds);

    @Modifying
    @Query("UPDATE Answer a SET a.selectionCount = a.selectionCount + 1 WHERE a.id = :answerId")
    int incrementSelectionCount(@Param("answerId") Long answerId);

    // Mise à jour du selectionCount pour plusieurs Answers spécifiques
    @Modifying
    @Query("UPDATE Answer a SET a.selectionCount = a.selectionCount + 1 WHERE a.id IN :answerIds")
    int incrementSelectionCountForAnswers(@Param("answerIds") List<Long> answerIds);
}
