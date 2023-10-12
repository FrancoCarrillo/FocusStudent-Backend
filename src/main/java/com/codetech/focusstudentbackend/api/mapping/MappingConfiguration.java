package com.codetech.focusstudentbackend.api.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("focusStudentMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public CourseMapper courseMapper() {
        return new CourseMapper();
    }

    @Bean
    public DetectorMapper detectorMapper() {
        return new DetectorMapper();
    }

    @Bean
    public CourseSectionMapper courseSectionMapper() {
        return new CourseSectionMapper();
    }

    @Bean
    public StudentMapper studentMapper() {
        return new StudentMapper();
    }

    @Bean
    public SectionMapper sectionMapper() {
        return new SectionMapper();
    }

    @Bean
    public LessonMapper lessonMapper() {
        return new LessonMapper();
    }

    @Bean
    public TeacherMapper teacherMapper() {
        return new TeacherMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

}
