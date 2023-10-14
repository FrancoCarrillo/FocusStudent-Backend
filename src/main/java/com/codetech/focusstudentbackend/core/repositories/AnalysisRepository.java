package com.codetech.focusstudentbackend.core.repositories;

import com.codetech.focusstudentbackend.api.model.responses.AnalysisGraphicResponse;
import com.codetech.focusstudentbackend.core.entities.Analysis;
import com.codetech.focusstudentbackend.core.enums.FaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
    @Query(value = "SELECT s.name AS sectionName, COUNT(*) AS total " +
            "FROM analysis a, detector d, lesson l, course_section cs, section s " +
            "WHERE a.detector.id = d.id " +
            "AND d.lesson.id = l.id " +
            "AND l.courseSection.id = cs.id " +
            "AND cs.section.id = s.id " +
            "AND a.faceStatus = :faceStatus " +
            "GROUP BY s.name")
    List<AnalysisGraphicResponse> findSectionTotals(@Param("faceStatus") FaceStatus faceStatus);


}
