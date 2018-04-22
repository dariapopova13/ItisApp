package com.university.itis.itisapp.model.builder;


import com.university.itis.itisapp.model.Token;

import java.util.Date;

public class TokenBuilder {

    private String token;
    private Date endDate;
    private Date startDate;
    private String username;

    public TokenBuilder setToken(String token) {
        this.token = token;
        return this;
    }

    public TokenBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public TokenBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public TokenBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public Token createToken() {
        return new Token(token, endDate, startDate, username);
    }
}