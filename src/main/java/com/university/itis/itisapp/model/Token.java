package com.university.itis.itisapp.model;

import com.university.itis.itisapp.model.common.AbstractEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created on 24.05.17.
 */
@DynamicInsert
@DynamicUpdate
@Table(name = "token")
@Entity
public class Token extends AbstractEntity {

    private String token;
    private Date endDate;
    private Date startDate;
    private String username;

    public Token() {
    }

    public Token(String token, Date endDate, Date startDate, String username) {
        this.token = token;
        this.endDate = endDate;
        this.startDate = startDate;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
