package com.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Future
    private LocalDate creationDate;
    @Future
    private LocalDate startDate;
    private int year;

    @ManyToOne
    private Survey survey;


    @OneToMany(mappedBy = "surveyEdition", fetch = FetchType.EAGER)
    private List<Chapter> chapters;

}
