package com.university.itis.itisapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.itisapp.dto.common.AbstractDto;
import com.university.itis.itisapp.model.Course;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDto extends AbstractDto {

    private String name;
    private ProfessorDto professor;
    private String info;

    public CourseDto(Course course) {
        super(course);
        this.name = course.getName();
        this.info = course.getInfo();
        if (course.getProfessor() != null)
            this.professor = new ProfessorDto(course.getProfessor());
    }

    public CourseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProfessorDto getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDto professor) {
        this.professor = professor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
