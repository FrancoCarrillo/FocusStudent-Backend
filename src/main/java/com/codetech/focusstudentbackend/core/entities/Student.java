package com.codetech.focusstudentbackend.core.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class Student extends User {
    @ManyToOne
    private Section section;

    @OneToMany(mappedBy = "student")
    private List<Detector> detectors;
}
