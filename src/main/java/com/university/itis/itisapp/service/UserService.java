package com.university.itis.itisapp.service;


import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.model.News;
import com.university.itis.itisapp.model.Token;
import com.university.itis.itisapp.model.User;

public interface UserService extends BaseService<User> {

    public String getCurrentUserUsername();

    public User getCurrentUser();

    Boolean ping(Token token);

    UserFormDto authenticate(UserFormDto userFormDto);

    boolean checkNews(News news);

    boolean checkCourse(Course course);
}
