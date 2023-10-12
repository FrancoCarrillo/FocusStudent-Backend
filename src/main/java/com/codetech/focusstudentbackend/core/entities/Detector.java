package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private String initialState;
    private String middleState;
    private String finalState;
    private Date detectionDate;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Lesson lesson;
}
