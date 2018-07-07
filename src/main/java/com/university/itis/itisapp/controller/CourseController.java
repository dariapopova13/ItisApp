package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "")
    public List<CourseDto> getAllById(@RequestParam(name = "id")List<Long> ids) {
        return courseService.getAllById(ids);
    }


    @PostMapping(value = "/api/add")
    public CourseDto addNewCourse(@RequestBody CourseDto courseDto) {
        return courseService.saveOrUdpate(courseDto);
    }

    @DeleteMapping(value = "/api/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }


    @GetMapping(value = "/api/single/{id}")
    public CourseDto getOne(@PathVariable Long id) {
        return courseService.get(id);
    }

    @GetMapping(value = "/api")
    public List<CourseDto> getAll() {
        return courseService.getAll();
    }

}
