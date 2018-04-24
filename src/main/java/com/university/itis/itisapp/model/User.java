package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "users")
@Indexed
public class User extends AbstractEntity {

    @Column(name = "name", nullable = false)
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String name;
    @Column(name = "surname", nullable = false)
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String surname;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @ManyToOne
    private Role role;
    @Column(name = "delete_date")
    private Date deleteDate;

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
