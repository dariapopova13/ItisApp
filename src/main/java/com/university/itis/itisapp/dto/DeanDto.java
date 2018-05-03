package com.university.itis.itisapp.dto;

import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.Dean;
import com.university.itis.itisapp.model.User;
import com.university.itis.itisapp.model.common.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.sound.sampled.Line;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeanDto extends AbstractDto {


    private UserDto user;
    private String info;

    public DeanDto(Dean dean) {
        super(dean);
        this.user = new UserDto(dean.getUser());
        this.info = dean.getInfo();
    }

    public DeanDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
