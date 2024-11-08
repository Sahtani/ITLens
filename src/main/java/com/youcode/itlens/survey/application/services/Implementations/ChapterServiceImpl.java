package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.common.services.GenericCrudServiceImpl;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterRequestDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResponseDTO;
import com.youcode.itlens.survey.application.services.ChapterService;
import com.youcode.itlens.survey.domain.entities.Chapter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Transactional
public class ChapterServiceImpl extends GenericCrudServiceImpl<Chapter, ChapterRequestDTO, ChapterResponseDTO, Long> implements ChapterService {
    public ChapterServiceImpl(JpaRepository<Chapter, Long> repository, GenericMapper<Chapter, ChapterRequestDTO, ChapterResponseDTO> mapper) {
        super(repository, mapper);
    }
}
