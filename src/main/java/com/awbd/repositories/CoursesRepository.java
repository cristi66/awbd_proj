package com.awbd.repositories;

import com.awbd.entities.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

    Optional<Courses> findById (long id);

    Optional<Courses> findByTitle (String title);

    Courses save(Courses course);
}
