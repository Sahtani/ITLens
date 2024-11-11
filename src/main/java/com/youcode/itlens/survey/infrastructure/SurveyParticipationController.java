package com.youcode.itlens.survey.infrastructure;

import com.youcode.itlens.survey.application.dtos.Participate.SurveyParticipationRequestDTO;
import com.youcode.itlens.survey.application.services.SurveyParticipationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveys")
@RequiredArgsConstructor
public class SurveyParticipationController {

    private SurveyParticipationService surveyParticipationService;

    /**
     * Endpoint to handle survey participation.
     *
     * @param request The survey participation request (containing responses).
     * @return A response indicating the success or failure of the operation.
     */
    @PostMapping("/participate")
    public ResponseEntity<String> participate(@RequestBody SurveyParticipationRequestDTO request) {
        try {
            // Process the survey participation
            surveyParticipationService.handleSurveyParticipation(request);

            // Return success response
            return ResponseEntity.ok("Survey participation successfully recorded.");
        } catch (EntityNotFoundException e) {
            // Handle entity not found exception (e.g., question or answer not found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
