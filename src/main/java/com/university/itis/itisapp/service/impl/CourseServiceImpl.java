package com.university.itis.itisapp.service.impl;

import com.university.itis.itisapp.dto.CourseDto;
import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.model.User;
import com.university.itis.itisapp.model.enums.RoleNames;
import com.university.itis.itisapp.repository.CourseRepository;
import com.university.itis.itisapp.repository.ProfessorRepository;
import com.university.itis.itisapp.service.CourseService;
import com.university.itis.itisapp.service.UserService;
import com.university.itis.itisapp.utils.DtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@PropertySource("classpath:application.properties")
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DtoUtils dtoUtils;
    @Value("${default_page_count}")
    private int pageCount;
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public CourseDto get(Long id) {
        Course course = courseRepository.findOne(id);
        return course == null ? null : new CourseDto(course);
    }

    @Override
    public List<CourseDto> getAll() {
        User user = userService.getCurrentUser();
        List<Course> courses;
        if (user.getRole().getSimpleName().equalsIgnoreCase(RoleNames.PROFESSOR.name())) {
            courses = courseRepository.findByProfessorUser(user);
        } else courses = courseRepository.findAll();
        return courses.stream().map(CourseDto::new).collect(Collectors.toList());
    }

    @Override
    public CourseDto saveOrUdpate(CourseDto courseDto) {
        Course course = dtoUtils.toEntity(courseDto);

        User current = userService.getCurrentUser();
        course.setProfessor(
                professorRepository.findByUserId(current.getId())
        );
        course = courseRepository.save(course);
        return course == null ? null : new CourseDto(course);
    }

    @Override
    public void delete(Long id) {
        Course course = courseRepository.findOne(id);
        if (course != null && userService.checkCourse(course)) {
            courseRepository.delete(course);
        }
    }
}
