package com.university.itis.itisapp.dao.impl;

import com.university.itis.itisapp.dao.SearchDao;
import com.university.itis.itisapp.model.Course;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseSearchDao implements SearchDao<Course> {

    @Override
    public List<Course> search(String request) throws InterruptedException {
        return null;
    }
}
