package com.university.itis.itisapp.utils;

import com.university.itis.itisapp.dto.*;
import com.university.itis.itisapp.model.*;
import com.university.itis.itisapp.repository.CourseRepository;
import com.university.itis.itisapp.repository.NewsRepository;
import com.university.itis.itisapp.repository.ProfessorRepository;
import com.university.itis.itisapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoUtils {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;

    public Course toEntity(CourseDto dto) {
        Course course;
        if (dto.getId() != null) {
            course = courseRepository.findOne(dto.getId());
        } else course = new Course();

        course.setInfo(dto.getInfo());
        course.setName(dto.getName());
//        course.setProfessor();

        return course;
    }

    public Professor toEntiry(ProfessorDto dto) {
        Professor professor;
        if (dto.getId() != null) {
            professor = professorRepository.findOne(dto.getId());
        } else professor = new Professor();
        professor.setInfo(dto.getInfo());

        if (dto.getUser() != null) {
            professor.setUser(toEntity(dto.getUser()));
        }
        return professor;
    }

    public User toEntity(UserDto dto) {
        User user;
        if (dto.getId() != null) {
            user = userRepository.findOne(dto.getId());
        } else user = new User();

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());

        return user;
    }

    public News toEntiry(NewsDto dto) {
        News news;
        if (dto.getId() != null) {
            news = newsRepository.findOne(dto.getId());
        } else news = new News();
        news.setDeadline(dto.getDeadline());
        news.setTitle(dto.getTitle());
        news.setNewsText(dto.getNewsText());

        news.setYear(dto.getYear());
        if (dto.getCourse() != null) {
            news.setCourse(toEntity(dto.getCourse()));
        }
        return news;
    }

//    public Credential toEntity(UserFormDto dto){
//        Credential credential = new Credential();
//
//
//
//    }

    public User toEntity(UserFormDto dto) {
        User user;
        if (dto.getId()!=null){
            user = userRepository.findOne(dto.getId());
        }else user = new User();
//        user.setCredential(toEntity(dto));

        return null;
    }
}
