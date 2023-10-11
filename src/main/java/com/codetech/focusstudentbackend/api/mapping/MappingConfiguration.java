package com.codetech.focusstudentbackend.api.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("focusStudentMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public CourseMapper courseMapper() { return new CourseMapper();}

}
