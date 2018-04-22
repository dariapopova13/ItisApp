package com.university.itis.itisapp.authentication;//package com.todoist.sql.server.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.Token;
import com.university.itis.itisapp.model.builder.TokenBuilder;
import com.university.itis.itisapp.repository.TokenRepository;
import com.university.itis.itisapp.service.impl.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Daria Popova on 10.05.17.
 */
@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        tokenAuthenticationService.addAuthentication(response, authentication.getName());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        UserFormDto userFormDto = new UserFormDto();

        userFormDto.setRole(authentication.getAuthorities()
                .stream().map(o -> o.getAuthority()).collect(Collectors.toList()).get(0));
        if (authentication.getPrincipal() instanceof UserDetails)
            userFormDto.setUsername(((UserDetails) authentication.getPrincipal()).getUsername());
        else userFormDto.setUsername((String) authentication.getPrincipal());
        Token token = tokenRepository.findByUsernameAndEndDateIsNull(userFormDto.getUsername());
        if (token == null) {
            tokenAuthenticationService.addAuthentication(userFormDto);
            token = new TokenBuilder()
                    .setUsername(userFormDto.getUsername())
                    .setStartDate(new Date())
                    .setToken(userFormDto.getToken())
                    .createToken();
            tokenRepository.save(token);
        } else userFormDto.setToken(token.getToken());
        response.getWriter().print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userFormDto));
    }
}
