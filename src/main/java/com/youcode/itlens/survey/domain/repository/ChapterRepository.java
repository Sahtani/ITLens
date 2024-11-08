package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.domain.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
