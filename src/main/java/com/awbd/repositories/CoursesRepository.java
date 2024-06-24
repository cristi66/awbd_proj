package com.awbd.repositories;

import com.awbd.entities.Courses;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoursesRepository extends CrudRepository<Courses, Long> {

    Optional<Courses> findById (long id);

    Optional<Courses> findByName (String name);

    Courses save(Courses course);

}
