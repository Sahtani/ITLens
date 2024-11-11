package com.youcode.itlens.survey.domain.exception;

public class ChapterHasSubChaptersException extends RuntimeException {
    public ChapterHasSubChaptersException(Long chapterId) {
        super("Questions can only be added to the last subchapter. Chapter with ID " + chapterId + " has subchapters.");
    }
}
