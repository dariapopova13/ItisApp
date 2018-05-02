package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/add")
    public CourseDto addNewCourse(CourseDto courseDto) {
        return courseService.saveOrUdpate(courseDto);
    }

    @GetMapping(value = "/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }
}
