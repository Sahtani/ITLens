package com.youcode.itlens.survey.application.services.Implementations;

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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SurveyEditionServiceImpl implements SurveyEditionService {

    private final SurveyEditionRepository repository;
    private final SurveyRepository surveyRepository;
    private final SurveyEditionMapper mapper;


    @Override
    public List<SurveyEditionResponseDTO> getAll() {
        return repository.findAll()
                .stream().map(mapper::toDto)
                .toList();
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
