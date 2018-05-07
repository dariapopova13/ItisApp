package com.university.itis.itisapp.repository;

import com.university.itis.itisapp.model.Role;
import com.university.itis.itisapp.model.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleNames name);
}
