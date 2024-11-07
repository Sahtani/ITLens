package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyEditionRepository extends JpaRepository<SurveyEdition, Long> {
}
