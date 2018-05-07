package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.UserDto;
import com.university.itis.itisapp.dto.UserFormDto;
import com.university.itis.itisapp.model.*;
import com.university.itis.itisapp.model.builder.TokenBuilder;
import com.university.itis.itisapp.model.common.AbstractEntity;
import com.university.itis.itisapp.model.enums.RoleNames;
import com.university.itis.itisapp.repository.ProfessorRepository;
import com.university.itis.itisapp.repository.TokenRepository;
import com.university.itis.itisapp.repository.UserRepository;
import com.university.itis.itisapp.service.MailService;
import com.university.itis.itisapp.service.UserService;
import com.university.itis.itisapp.utils.DtoUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@PropertySource("classpath:application.properties")
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
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${default_page_count}")
    private int pageCount;

    @Override
    public UserDto get(Long id) {
        User user = userRepository.findOne(id);
        return user == null ? null : new UserDto(user);
    }

    @Override
    public UserDto saveOrUdpate(UserDto dto) {
        User user = dtoUtils.toEntity(dto);
        User current = getCurrentUser();
        if (current.getId().equals(user.getId()))
            user = userRepository.save(user);
        return user == null ? null : new UserDto(user);
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {
        User user = dtoUtils.toEntity(userDto);
        String password = RandomStringUtils.random(15, true, true);
        user.setPassword(passwordEncoder.encode(password));
        if (user.getRole().getSimpleName().equalsIgnoreCase(RoleNames.PROFESSOR.name())) {
            Professor professor = new Professor();
            professor.setUser(user);
            professorRepository.save(professor);
            user = userRepository.findOne(professor.getUser().getId());
        } else {
            user = userRepository.save(user);
        }
        if (user != null) {
            mailService.sendConfirmationMail(user, password);
        }
        return new UserDto(user);
    }

    @Override
    public List<UserDto> getAll(int page) {
        Pageable request = new PageRequest(page, pageCount);
        return userRepository.findAll(request).map(UserDto::new).getContent();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(String.format("Username '%s' not found", email));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getSimpleName()));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }


    @Override
    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
        String username = getCurrentUserEmail();
        if (username == null) return null;
        return userRepository.findByEmail(username);
    }

    @Override
    public Boolean ping(Token token) {
        return tokenRepository.findByEmailAndTokenAndEndDateIsNull(
                token.getEmail(), token.getToken()) != null;
    }

    @Override
    public UserFormDto authenticate(UserFormDto userFormDto) {
        if (userFormDto.getId() == null)
            return null;
        tokenService.addAuthentication(userFormDto);
        Token token = new TokenBuilder()
                .setEmail(userFormDto.getEmail())
                .setStartDate(new Date())
                .setToken(userFormDto.getToken())
                .createToken();
        tokenRepository.save(token);
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(userFormDto.getPassword(),
                        userFormDto.getPassword(), Collections.emptyList());
        return userFormDto;
    }

    @Override
    public boolean checkNews(News news) {
        User current = getCurrentUser();
        if (current != null) {
            if (news.getCourse() != null && current.getRole()
                    .getSimpleName().equals(RoleNames.PROFESSOR.name())) {
                Professor professor = professorRepository.findByUserEmail(current.getEmail());
                if (professor != null) {
                    return professor.getCourses()
                            .stream().map(AbstractEntity::getId)
                            .collect(Collectors.toSet())
                            .contains(news.getCourse().getId());
                }

            } else if (news.getYear() != null && current.getRole()
                    .getSimpleName().equals(RoleNames.DEAN.name())) {
                return true;
            } else return current.getRole().getSimpleName().equals(RoleNames.ADMIN.name());
        }
        return false;
    }

    @Override
    public boolean checkCourse(Course course) {
        User current = getCurrentUser();
        if (current != null) {
            if (course != null && current.getRole()
                    .getSimpleName().equals(RoleNames.PROFESSOR.name())) {
                Professor professor = professorRepository.findByUserEmail(current.getEmail());
                if (professor != null) {
                    return professor.getCourses()
                            .stream().map(AbstractEntity::getId)
                            .collect(Collectors.toSet())
                            .contains(course.getId());
                }

            } else return current.getRole().getSimpleName().equals(RoleNames.ADMIN.name());
        }
        return false;
    }
}
