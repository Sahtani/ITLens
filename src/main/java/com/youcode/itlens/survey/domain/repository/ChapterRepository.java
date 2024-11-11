package com.youcode.itlens.survey.domain.repository;

import com.youcode.itlens.survey.domain.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findAllBySurveyEditionId(Long editionId);
    Optional<Chapter> findByTitleAndSurveyEditionId(String title, Long surveyEditionId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Chapter c WHERE c.parentChapter.id = :chapterId")
    boolean hasSubChapters(@Param("chapterId") Long chapterId);
}
