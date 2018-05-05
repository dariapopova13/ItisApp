package com.university.itis.itisapp.service;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.model.Course;

import java.util.List;

public interface CourseService extends BaseService<CourseDto> {

    List<CourseDto> getAll();

}
