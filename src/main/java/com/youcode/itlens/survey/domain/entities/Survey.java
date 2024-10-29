package com.youcode.itlens.survey.domain.entities;

import com.youcode.itlens.owner.domain.entities.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private Set<SurveyEdition> editions;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private Set<Chapter> chapters;

}
