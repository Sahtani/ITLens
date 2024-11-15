package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.survey.application.dtos.PagedResponse;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionRequestDTO;
import com.youcode.itlens.survey.application.dtos.SurveyEdition.SurveyEditionResponseDTO;
import com.youcode.itlens.survey.application.mappers.SurveyEditionMapper;
import com.youcode.itlens.survey.application.services.SurveyEditionService;
import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.entities.SurveyEdition;
import com.youcode.itlens.survey.domain.repository.SurveyEditionRepository;
import com.youcode.itlens.survey.domain.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SurveyEditionServiceImpl implements SurveyEditionService {

    private final SurveyEditionRepository repository;
    private final SurveyRepository surveyRepository;
    private final SurveyEditionMapper mapper;


    @Override
    public PagedResponse<SurveyEditionResponseDTO> getAll(Pageable pageable) {
        Page<SurveyEdition> surveyEditionsPage = repository.findAll(pageable);
        List<SurveyEditionResponseDTO> surveyEditionResponseDTOS = surveyEditionsPage.getContent()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                surveyEditionResponseDTOS,
                surveyEditionsPage.getNumber(),
                surveyEditionsPage.getSize(),
                surveyEditionsPage.getTotalElements(),
                surveyEditionsPage.getTotalPages(),
                surveyEditionsPage.isLast()
        );
    }


    @Override
    public SurveyEditionResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("survey edition  "));
    }

    @Override
    public SurveyEditionResponseDTO save(SurveyEditionRequestDTO surveyEditionRequestDTO) {
        Survey survey = surveyRepository.findById(surveyEditionRequestDTO.surveyId())
                .orElseThrow(() -> new EntityNotFoundException("survey"));

        SurveyEdition surveyEdition = mapper.toEntity(surveyEditionRequestDTO)
                .setSurvey(survey);
        SurveyEdition savedEdition = repository.save(surveyEdition);
        return mapper.toDto(savedEdition);
    }

    @Override
    public SurveyEditionResponseDTO update(Long id, SurveyEditionRequestDTO surveyEditionRequestDTO) {
        SurveyEdition surveyEdition = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("survey edition"));
        Survey survey = surveyRepository.findById(surveyEditionRequestDTO.surveyId())
                .orElseThrow(() -> new EntityNotFoundException("survey"));

        surveyEdition.setCreationDate(surveyEditionRequestDTO.creationDate())
                .setStartDate(surveyEditionRequestDTO.startDate())
                .setYear(surveyEditionRequestDTO.year())
                .setSurvey(survey);

        return mapper.toDto(surveyEdition);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("survey edition");
        repository.deleteById(id);
    }
}
