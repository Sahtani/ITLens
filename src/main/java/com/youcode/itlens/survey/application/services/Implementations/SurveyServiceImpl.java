package com.youcode.itlens.survey.application.services.Implementations;

import com.youcode.itlens.owner.domain.Owner;
import com.youcode.itlens.owner.domain.OwnerRepository;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.application.mappers.SurveyMapper;
import com.youcode.itlens.survey.application.services.SurveyService;
import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
@Validated
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;
    private final OwnerRepository ownerRepository;
    private final SurveyMapper mapper;

    @Override
    public List<SurveyResponseDTO> getAll() {

        return repository.findAll()
                .stream().map(mapper::toDto)
                .toList();
    }

    @Override
    public SurveyResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("survey"));
    }

    @Override
    public SurveyResponseDTO save(SurveyRequestDTO surveyRequestDTO) {
        Owner owner = ownerRepository.findById(surveyRequestDTO.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("owner"));
        Survey survey = mapper.toEntity(surveyRequestDTO)
                .setOwner(owner);

        Survey savedSurvey = repository.save(survey);
        return mapper.toDto(savedSurvey);
    }

    @Override
    public SurveyResponseDTO update(Long id, SurveyRequestDTO surveyRequestDTO) {
        return null;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
