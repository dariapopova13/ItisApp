package com.university.itis.itisapp.repository;

import com.university.itis.itisapp.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetabelRepository extends JpaRepository<Timetable,Long> {


    List<Timetable> findByGroup(String group);
}
