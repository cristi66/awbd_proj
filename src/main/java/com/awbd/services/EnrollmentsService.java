package com.awbd.services;

import com.awbd.dtos.EnrollmentsDTO;
import com.awbd.entities.Courses;
import com.awbd.entities.Enrollments;

import java.util.List;

public interface EnrollmentsService {

    //List<EnrollmentsDTO> findByIdIn(List<Long> ids);

    List<EnrollmentsDTO> findAllByUserId(Long userId);

    EnrollmentsDTO save(EnrollmentsDTO enrollment);

    void deleteByCourseIdAndUserId(Long courseId, Long userId);
}
