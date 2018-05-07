package com.university.itis.itisapp.repository;

import com.university.itis.itisapp.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findByUserId(Long id);

    Professor findByUserEmail(String email);
}
