package com.university.itis.itisapp.authentication;//package com.todoist.sql.server.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.university.itis.itisapp.dto.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Daria Popova on 10.05.17.
 */
public class RestUserNameAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private ObjectMapper objectMapper;
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            UserFormDto userFormDto = null;
            try {
                userFormDto = objectMapper.readValue(request.getReader(), UserFormDto.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (userFormDto == null || userFormDto.getPassword() == null || userFormDto.getUsername() == null)
                throw new AuthenticationCredentialsNotFoundException("Bad credentials");
            String username = userFormDto.getUsername();
            String password = userFormDto.getPassword();

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

}
