package com.codetech.focusstudentbackend.core.entities;

import com.codetech.focusstudentbackend.core.enums.ConcentrateStatus;
import com.codetech.focusstudentbackend.core.enums.FaceStatus;
import com.codetech.focusstudentbackend.core.enums.TimeStatus;
import lombok.*;

import javax.persistence.*;

@Entity(name = "analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    TimeStatus timeStatus;
    @Enumerated(EnumType.STRING)
    ConcentrateStatus concentrateStatus;
    @Enumerated(EnumType.STRING)
    FaceStatus faceStatus;

    @ManyToOne
    Detector detector;
}
