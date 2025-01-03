package com.youcode.itlens.survey.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
@Entity
@Table(name = "chapters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private Chapter parentChapter;

    @OneToMany(mappedBy = "parentChapter")
    private List<Chapter> subChapters ;


    @ManyToOne
   private SurveyEdition surveyEdition;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Question> questions;

    public boolean isSubChapter() {
        return parentChapter != null;
    }

}
