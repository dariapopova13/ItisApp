package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dao.SearchDao;
import com.university.itis.itisapp.dao.impl.NewsSearchDao;
import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.dto.NewsDto;
import com.university.itis.itisapp.dto.SimpleCourseDto;
import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.model.News;
import com.university.itis.itisapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    @Qualifier(value = "newsSearchDao")
    private SearchDao<News> newsSearchDao;
    @Autowired
    @Qualifier(value = "courseSearchDao")
    private SearchDao<Course> courseSearchDao;

    @Override
    public List<NewsDto> searchNews(String q) {
        try {
            return newsSearchDao.search(q).stream().map(NewsDto::new).collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<SimpleCourseDto> searchCourses(String q) {
        try {
            return courseSearchDao.search(q).stream().map(SimpleCourseDto::new).collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

}
