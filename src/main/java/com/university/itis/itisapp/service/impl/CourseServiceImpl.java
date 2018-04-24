package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.repository.CourseRepository;
import com.university.itis.itisapp.service.CourseService;
import com.university.itis.itisapp.service.UserService;
import com.university.itis.itisapp.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DtoUtils dtoUtils;

    @Override
    public CourseDto get(Long id) {
        Course course = courseRepository.findOne(id);
        return course == null ? null : new CourseDto(course);
    }

    @Override
    public CourseDto saveOrUdpate(CourseDto courseDto) {
        Course course = dtoUtils.toEntity(courseDto);
        course = courseRepository.save(course);
        return course == null ? null : new CourseDto(course);
    }

    @Override
    public void delete(Long id) {
        Course course = courseRepository.findOne(id);
        if (course != null && userService.checkCourse(course)) {
            course.setDeleteDate(new Date());
            courseRepository.save(course);
        }
    }
}
