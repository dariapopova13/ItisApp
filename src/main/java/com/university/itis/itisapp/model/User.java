package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "users")
@Indexed
public class User extends AbstractEntity {

    @Column(name = "name")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String name;
    @Column(name = "surname")
    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES,
            analyzer = @Analyzer(definition = "customanalyzer"))
    private String surname;
    @Column(name = "password")
    private String password;
    @ManyToOne
    private Role role;
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
