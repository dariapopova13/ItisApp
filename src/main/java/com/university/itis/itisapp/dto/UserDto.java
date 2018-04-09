package com.university.itis.itisapp.dto;

import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.Credential;
import com.university.itis.itisapp.model.User;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends AbstractDto {

    private String name;
    private String surname;

    public UserDto(User user){
        super(user);

    }

    public UserDto(){

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
