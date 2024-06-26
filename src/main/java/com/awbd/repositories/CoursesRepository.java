package com.awbd.repositories;

import com.awbd.entities.Courses;
import com.awbd.enums.CourseTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

    Optional<Courses> findById (long id);

    List<Courses> findAllByType (CourseTypeEnum type);

    Optional<Courses> findByTitle (String title);

    Courses save(Courses course);
}
