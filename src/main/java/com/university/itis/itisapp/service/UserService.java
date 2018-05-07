package com.university.itis.itisapp.service;


import com.university.itis.itisapp.dto.UserDto;
import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.model.News;
import com.university.itis.itisapp.model.Token;
import com.university.itis.itisapp.model.User;

import java.util.List;

public interface UserService extends BaseService<UserDto> {

    public String getCurrentUserEmail();

    public User getCurrentUser();

    Boolean ping(Token token);

    UserFormDto authenticate(UserFormDto userFormDto);

    boolean checkNews(News news);

    boolean checkCourse(Course course);

    public List<UserDto> getAll(int page);

    UserDto saveNewUser(UserDto userDto);
}
