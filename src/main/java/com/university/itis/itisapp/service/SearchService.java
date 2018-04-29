package com.university.itis.itisapp.service;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.SimpleCourseDto;

import java.util.List;

public interface SearchService {


    List<NewsDto> searchNews(String q);

    List<SimpleCourseDto> searchCourses(String q);
}
