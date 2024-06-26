package com.awbd.services;


import com.awbd.dtos.CoursesDTO;
import com.awbd.entities.Courses;
import com.awbd.enums.CourseTypeEnum;

import java.util.List;

public interface CoursesService {
    List<CoursesDTO> findAll();
    List<CoursesDTO> findAllByType(CourseTypeEnum type);

    CoursesDTO findById(Long id);

    CoursesDTO save(CoursesDTO course);

    void deleteById(Long id);

    CoursesDTO updateCourseById(Long id, CoursesDTO course);
}
