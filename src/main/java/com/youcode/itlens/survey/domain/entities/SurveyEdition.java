package com.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.security.auth.Subject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "surveyeditions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private Year year;

    @ManyToOne
    private Survey survey;


    @OneToMany(mappedBy = "surveyEdition", fetch = FetchType.EAGER)
    private List<Chapter> chapters;

}
