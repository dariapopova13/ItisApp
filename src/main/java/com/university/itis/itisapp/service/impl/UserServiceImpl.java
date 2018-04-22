package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.Token;
import com.university.itis.itisapp.model.User;
import com.university.itis.itisapp.model.builder.TokenBuilder;
import com.university.itis.itisapp.repository.TokenRepository;
import com.university.itis.itisapp.repository.UserRepository;
import com.university.itis.itisapp.service.UserService;
import com.university.itis.itisapp.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenAuthenticationService tokenService;
    @Autowired
    private DtoUtils dtoUtils;

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User saveOrUdpate(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("Username '%s' not found", username));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getSimpleName()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public String getCurrentUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUserUsername();
        if (username == null) return null;
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean ping(Token token) {
        return tokenRepository.findByUsernameAndTokenAndEndDateIsNull(token.getUsername(), token.getToken()) != null;
    }
//
//    @Override
//    public UserFormDto signUp(UserFormDto userFormDto) {
//        if (loadUserByUsername(userFormDto.getUsername()) != null) {
//        }
//        return new UserFormDto(saveOrUdpate(dtoUtils.toEntity(userFormDto)));
//    }


    @Override
    public UserFormDto authenticate(UserFormDto userFormDto) {
        if (userFormDto.getId() == null)
            return null;
        tokenService.addAuthentication(userFormDto);
        Token token = new TokenBuilder()
                .setUsername(userFormDto.getUsername())
                .setStartDate(new Date())
                .setToken(userFormDto.getToken())
                .createToken();
        tokenRepository.save(token);
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(userFormDto.getPassword(),
                        userFormDto.getPassword(), Collections.emptyList());
        return userFormDto;
    }


}
