package com.university.itis.itisapp.service;


import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.Token;
import com.university.itis.itisapp.model.User;

public interface UserService extends BaseService<User> {

    public String getCurrentUserUsername();

    public User getCurrentUser();

    Boolean ping(Token token);

    UserFormDto authenticate(UserFormDto userFormDto);
}
