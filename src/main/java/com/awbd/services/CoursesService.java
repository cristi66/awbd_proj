package com.awbd.services;


import com.awbd.dtos.CoursesDTO;
import com.awbd.entities.Courses;

import java.util.List;

public interface CoursesService {
    List<CoursesDTO> findAll();

    CoursesDTO findById(Long id);

    CoursesDTO save(CoursesDTO course);

    void deleteById(Long id);

    CoursesDTO updateCourseById(Long id, CoursesDTO course);
}
