package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/api/add")
    public CourseDto addNewCourse(CourseDto courseDto) {
        return courseService.saveOrUdpate(courseDto);
    }

    @DeleteMapping(value = "/api/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }


    @GetMapping(value = "/api/single/{id}")
    public CourseDto getOne(@PathVariable Long id){
        return courseService.get(id);
    }

    @GetMapping(value = "/api")
    public List<CourseDto> getAll(){
        return courseService.getAll();
    }

}
