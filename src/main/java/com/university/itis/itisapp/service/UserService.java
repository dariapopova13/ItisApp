package com.university.itis.itisapp.service;


import com.university.itis.itisapp.model.User;

public interface UserService extends BaseService<User> {

    public String getCurrentUserUsername();

    public User getCurrentUser();

}
