package com.university.itis.itisapp.dto;

import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.Professor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessorDto extends AbstractDto {

    private UserDto user;
    private String info;
    private List<SimpleCourseDto> courses;


    public ProfessorDto(Professor professor) {
        super(professor);
        if (professor.getUser() != null)
            this.user = new UserDto(professor.getUser());
        this.info = professor.getInfo();
        this.courses = professor.getCourses().stream()
                .map(SimpleCourseDto::new).collect(Collectors.toList());
    }

    public ProfessorDto() {
    }

    public List<SimpleCourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<SimpleCourseDto> courses) {
        this.courses = courses;
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
