package com.youcode.itlens.survey.application.dtos.Chapter;

import java.util.List;

public record ChapterResultDTO(String title,
                               List<SubChapterResultDTO> subChapters) {
}
