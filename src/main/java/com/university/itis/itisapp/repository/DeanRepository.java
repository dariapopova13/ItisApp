package com.university.itis.itisapp.repository;

import com.university.itis.itisapp.model.Dean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeanRepository extends JpaRepository<Dean, Long> {

    Dean findByUserId(Long id);

    Dean findByUserUsername(String username);
}
