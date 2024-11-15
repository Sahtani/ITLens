package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.common.services.GenericCrudServiceImpl;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterRequestDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResponseDTO;
import com.youcode.itlens.survey.application.mappers.ChapterMapper;
import com.youcode.itlens.survey.application.services.ChapterService;
import com.youcode.itlens.survey.domain.entities.Chapter;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;
import com.youcode.itlens.survey.domain.exception.DuplicateChapterTitleException;
import com.youcode.itlens.survey.domain.repository.ChapterRepository;
import com.youcode.itlens.survey.domain.repository.SurveyEditionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@Transactional
public class ChapterServiceImpl extends GenericCrudServiceImpl<Chapter, ChapterRequestDTO, ChapterResponseDTO, Long> implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final SurveyEditionRepository surveyEditionRepository;
    private final ChapterMapper mapper;

    public ChapterServiceImpl(ChapterRepository chapterRepository, ChapterMapper mapper, SurveyEditionRepository surveyEditionRepository) {
        super(chapterRepository, mapper);
        this.chapterRepository = chapterRepository;
        this.surveyEditionRepository = surveyEditionRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ChapterResponseDTO> findAllBySurveyEditionId(Long editionId) {
        if (!surveyEditionRepository.existsById(editionId)) {
            throw new EntityNotFoundException("Survey Edition not found for ID: " + editionId);
        }

        return chapterRepository.findAllBySurveyEditionId(editionId).stream().map(mapper::toDto).toList();
    }

    @Override
    public ChapterResponseDTO save(ChapterRequestDTO requestDto) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(requestDto.surveyEditionId()).orElseThrow(() -> new EntityNotFoundException("Survey edition with ID " + requestDto.surveyEditionId() + " not found"));

        chapterRepository.findByTitleAndSurveyEditionId(requestDto.title(), requestDto.surveyEditionId()).ifPresent(chapter -> {
            throw new DuplicateChapterTitleException(requestDto.title(), requestDto.surveyEditionId());
        });

        Chapter chapter = mapper.toEntity(requestDto);
        chapter.setSurveyEdition(surveyEdition);

        // If parentChapterId is provided, set the parent chapter
        if (requestDto.parentChapterId() != null) {
            Chapter parentChapter = chapterRepository.findById(requestDto.parentChapterId()).orElseThrow(() -> new EntityNotFoundException("Parent chapter with ID " + requestDto.parentChapterId() + " not found"));
            chapter.setParentChapter(parentChapter);
        }
        Chapter savedChapter = chapterRepository.save(chapter);
        return mapper.toDto(savedChapter);
    }
    @Override
    public ChapterResponseDTO update(Long id, ChapterRequestDTO dto) {
        Chapter chapter = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Chapter not found for ID:  " + id));

        chapter.setTitle(dto.title());
        return mapper.toDto(chapter);
    }


}
