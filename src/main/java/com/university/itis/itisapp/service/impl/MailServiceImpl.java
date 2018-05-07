package com.university.itis.itisapp.service.impl;


import com.university.itis.itisapp.model.User;
import com.university.itis.itisapp.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created on 17.05.17.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendConfirmationMail(User user, String password) {
        mailSender.send(createMessage(user, password));
    }

    private SimpleMailMessage createMessage(User user, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(EmailProperties.Email);
        message.setSubject(EmailProperties.Subject);

        String emailMessage = "Вы были зарегистрированы на сайте 'ItisApp'.\n" +
                "Ваш пароль: " + password + "\n" +
                "Ваш email: " + user.getEmail() + "\n" +
                "После  первого входа в систему рекомендуется сменить пароль.";

        message.setText(emailMessage);
        message.setTo(user.getEmail());
        return message;
    }

    public class EmailProperties {

        public static final String Email = "itis.news.app@gmail.com";
        public static final String Subject = "ItisApp credentials";
    }

}
