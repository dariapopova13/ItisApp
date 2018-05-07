package com.university.itis.itisapp.service;

import com.university.itis.itisapp.model.User;

/**
 * Created on 17.05.17.
 */
public interface MailService {

    void sendConfirmationMail(User user, String password);
}
