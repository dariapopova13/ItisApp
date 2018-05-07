package com.university.itis.itisapp.repository;

import com.university.itis.itisapp.model.Course;
import com.university.itis.itisapp.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findAllByCourseIdInOrYearOrderByDeadlineAsc(List<Long> courseIds, Integer year);


    @Query(value = "SELECT n FROM News n WHERE n.deadline between ?1 and ?2 " +
            "and (n.course.id in ?3 or n.year=?4 or n.year=0) order by n.deadline asc ")
    List<News> getNews(Date begin, Date end, List<Long> courseIds, Integer year);


    Page<News> findAllByYear(Integer year, Pageable pageable);

    Page<News> findAllByCourseIn(Collection<Course> courses, Pageable pageable);

    Page<News> findByYearNotNullOrderByDeadlineAsc(Pageable pageable);

}
