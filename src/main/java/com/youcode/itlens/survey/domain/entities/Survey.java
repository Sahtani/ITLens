package com.youcode.itlens.survey.domain.entities;

import com.youcode.itlens.owner.domain.Owner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "surveys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<SurveyEdition> surveyEditions ;


}
