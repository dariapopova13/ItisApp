package com.university.itis.itisapp.repository;

import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByProfessorUser(User user);

    List<Course> findByIdIn(List<Long> id);
}
