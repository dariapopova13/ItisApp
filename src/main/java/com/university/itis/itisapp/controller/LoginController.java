package com.university.itis.itisapp.controller;


import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.Token;
import com.university.itis.itisapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @PostMapping(value = "/ping")
    public Boolean ping(@RequestBody Token token) {
        return userService.ping(token);
    }

}