package com.youcode.itlens;

import com.youcode.itlens.owner.application.dtos.OwnerEmbeddableDTO;
import com.youcode.itlens.owner.domain.Owner;
import com.youcode.itlens.owner.domain.OwnerRepository;
import com.youcode.itlens.survey.application.dtos.PagedResponse;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyRequestDTO;
import com.youcode.itlens.survey.application.dtos.Survey.SurveyResponseDTO;
import com.youcode.itlens.survey.application.mappers.SurveyMapper;
import com.youcode.itlens.survey.application.services.Implementations.SurveyServiceImpl;
import com.youcode.itlens.survey.domain.entities.Survey;
import com.youcode.itlens.survey.domain.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private SurveyMapper mapper;

    private Owner owner;
    private OwnerEmbeddableDTO ownerEmbeddableDTO;
    private SurveyRequestDTO surveyRequestDTO;
    private SurveyResponseDTO surveyResponseDTO;
    private Survey survey;
    private Survey savedSurvey;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(123L);
        owner.setName("Owner Name");
        ownerEmbeddableDTO = new OwnerEmbeddableDTO(123L, "Owner Name");
        surveyRequestDTO = new SurveyRequestDTO("Survey Title", "Survey Description", 123L);

        surveyResponseDTO = new SurveyResponseDTO(1L, "Survey Title", "Survey Description", ownerEmbeddableDTO);

        survey = new Survey();
        survey.setTitle("Survey Title");
        survey.setDescription("Survey Description");

        savedSurvey = new Survey();
        savedSurvey.setId(1L);
        savedSurvey.setTitle("Survey Title");
        savedSurvey.setDescription("Survey Description");
    }


    @Test
    void testGetAll() {

        Page<Survey> surveyPage = new PageImpl<>(List.of(survey));
        Pageable pageable = PageRequest.of(0, 10);

        when(surveyRepository.findAll(pageable)).thenReturn(surveyPage);
        when(mapper.toDto(survey)).thenReturn(surveyResponseDTO);

        PagedResponse<SurveyResponseDTO> result = surveyService.getAll(pageable);

        assertEquals(1, result.content().size());
        verify(surveyRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetById() {
        when(surveyRepository.findById(1L)).thenReturn(Optional.of(survey));
        when(mapper.toDto(survey)).thenReturn(surveyResponseDTO);

        SurveyResponseDTO result = surveyService.getById(1L);

        assertNotNull(result);
        verify(surveyRepository, times(1)).findById(1L);
    }

    @Test
    void testGetByIdNotFound() {
        when(surveyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> surveyService.getById(1L));
        verify(surveyRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        when(mapper.toEntity(surveyRequestDTO)).thenReturn(survey);
        when(surveyRepository.save(survey)).thenReturn(savedSurvey);
        when(mapper.toDto(savedSurvey)).thenReturn(surveyResponseDTO);

        SurveyResponseDTO result = surveyService.save(surveyRequestDTO);

        assertNotNull(result);
        verify(ownerRepository, times(1)).findById(anyLong());
        verify(surveyRepository, times(1)).save(survey);
    }

    @Test
    void testUpdate() {
        when(surveyRepository.findById(anyLong())).thenReturn(Optional.of(survey));
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        when(mapper.toDto(survey)).thenReturn(surveyResponseDTO);

        SurveyResponseDTO result = surveyService.update(1L, surveyRequestDTO);

        assertNotNull(result);
        verify(surveyRepository, times(1)).findById(anyLong());
        verify(ownerRepository, times(1)).findById(anyLong());
    }

    @Test
    void testDelete() {
        when(surveyRepository.existsById(1L)).thenReturn(true);

        surveyService.delete(1L);

        verify(surveyRepository, times(1)).existsById(1L);
        verify(surveyRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        when(surveyRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> surveyService.delete(1L));
        verify(surveyRepository, times(1)).existsById(1L);
    }
}
