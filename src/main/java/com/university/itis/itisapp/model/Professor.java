package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "professor")
@Indexed
public class Professor extends AbstractEntity {

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    @IndexedEmbedded
    private User user;
    @Column(name = "info")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String info;
    @Column(name = "delete_date")
    private Date deleteDate;

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
