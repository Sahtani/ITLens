package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.common.annotations.declarations.Existe;
import com.youcode.itlens.common.controllers.GenericController;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterRequestDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResponseDTO;
import com.youcode.itlens.survey.application.services.ChapterService;
import com.youcode.itlens.survey.domain.entities.Chapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chapters")
public class ChapterController extends GenericController<ChapterRequestDTO, ChapterResponseDTO, Long> {

    public ChapterController(ChapterService chapterService) {
        super(chapterService);
    }

    @DeleteMapping("/{id}")
    @Existe(entityClass = Chapter.class, fieldName = "id", message = "The specified chapter does not exist.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
