package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "course")
@Indexed
public class Course extends AbstractEntity {

    @Column(name = "name")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String name;
    @ManyToOne
    @IndexedEmbedded
    private Professor professor;
    @Column(name = "info")
    @Lob
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
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
