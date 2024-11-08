package com.youcode.itlens.survey.application.mappers;

import com.youcode.itlens.common.mappers.GenericMapper;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterRequestDTO;
import com.youcode.itlens.survey.application.dtos.Chapter.ChapterResponseDTO;
import com.youcode.itlens.survey.domain.entities.Chapter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper extends GenericMapper<Chapter, ChapterRequestDTO, ChapterResponseDTO> {
}
