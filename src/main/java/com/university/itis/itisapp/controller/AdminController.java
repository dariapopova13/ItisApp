package com.university.itis.itisapp.controller;

import com.university.itis.itisapp.dto.UserDto;
import com.university.itis.itisapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users/api")
    public List<UserDto> getUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "/users/api/single/{id}")
    public UserDto getOne(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping(value = "/users/api/add")
    public UserDto save(@RequestBody UserDto userDto) {
        return userService.saveOrUdpate(userDto);
    }

    @PostMapping(value = "/users/api/add/new")
    public UserDto saveNew(@RequestBody UserDto userDto) {
        return userService.saveNewUser(userDto);
    }
}
