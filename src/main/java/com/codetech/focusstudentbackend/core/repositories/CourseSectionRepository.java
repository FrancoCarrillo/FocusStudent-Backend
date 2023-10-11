package com.codetech.focusstudentbackend.core.repositories;

import com.codetech.focusstudentbackend.core.entities.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSectionRepository extends JpaRepository<CourseSection, Long> {
    List<CourseSection> findAllByTeacherId(Long teacherId);
}
