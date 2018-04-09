package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "course")
public class Course extends AbstractEntity {

    @Column(name = "name")
    private String name;
    @ManyToOne
    private Professor professor;
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private Set<News> courseNews = new HashSet<>();

    public Set<News> getCourseNews() {
        return courseNews;
    }

    public void setCourseNews(Set<News> courseNews) {
        this.courseNews = courseNews;
    }

    @Column(name = "info")
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
