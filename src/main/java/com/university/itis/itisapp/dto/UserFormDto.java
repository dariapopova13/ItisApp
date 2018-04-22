package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.Role;
import com.university.itis.itisapp.model.User;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFormDto extends AbstractDto {

    private String username;
    private String password;
    private String role;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserFormDto() {
    }

    public UserFormDto(User user) {
        super(user);
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().getSimpleName();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
