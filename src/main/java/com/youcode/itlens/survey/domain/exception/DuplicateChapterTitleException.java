package com.youcode.itlens.survey.domain.exception;

public class DuplicateChapterTitleException extends RuntimeException {
    public DuplicateChapterTitleException(String title, Long surveyEditionId) {
        super("A chapter with the title '" + title + "' already exists in survey edition with ID " + surveyEditionId);
    }
}
