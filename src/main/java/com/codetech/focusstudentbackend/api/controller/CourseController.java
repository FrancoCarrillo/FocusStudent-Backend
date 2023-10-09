package com.codetech.focusstudentbackend.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/course")
@AllArgsConstructor
@Tag(name = "Course")
public class CourseController {

}