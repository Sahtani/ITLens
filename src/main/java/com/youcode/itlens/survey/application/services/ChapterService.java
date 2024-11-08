package com.youcode.itlens.survey.application.services;

import com.youcode.itlens.common.services.CrudService;
import com.youcode.itlens.common.services.GenericCrudServiceImpl;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterRequestDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResponseDTO;
import com.youcode.itlens.survey.domain.entities.Chapter;

public interface ChapterService extends CrudService<ChapterRequestDTO, ChapterResponseDTO,Long> {
}
