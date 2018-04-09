package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.repository.CourseRepository;
import com.university.itis.itisapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDto get(Long id) {
        return null;
    }

    @Override
    public CourseDto saveOrUdpate(CourseDto courseDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
