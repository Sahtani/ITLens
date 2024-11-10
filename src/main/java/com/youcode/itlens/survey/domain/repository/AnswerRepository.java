package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.domain.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
