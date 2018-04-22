package com.university.itis.itisapp.dto;

import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.User;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends AbstractDto {

    private String name;
    private String surname;
    private String username;

    public UserDto(User user){
        super(user);
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getSurname();
    }

    public UserDto(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
