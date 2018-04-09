package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.model.User;
import com.university.itis.itisapp.repository.UserRepository;
import com.university.itis.itisapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

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
        User user = userRepository.findByCredentialUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("Username '%s' not found", username));
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getSimpleName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getCredential().getUsername(), user.getCredential().getPassword(), authorities);
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
        if (username==null) return null;
        return userRepository.findByCredentialUsername(username);
    }
}
