package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.domain.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
