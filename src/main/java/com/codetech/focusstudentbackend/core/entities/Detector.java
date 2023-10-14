package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "detector")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class Detector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date start;
    private Date end;

    @OneToMany(mappedBy = "detector")
    Set<Analysis> analysis = new HashSet<>();

    @ManyToOne
    private Student student;

    @ManyToOne
    private Lesson lesson;
}
