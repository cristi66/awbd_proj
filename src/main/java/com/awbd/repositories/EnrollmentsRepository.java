package com.awbd.repositories;

import com.awbd.dtos.EnrollmentsDTO;
import com.awbd.entities.Enrollments;
import com.awbd.entities.Users;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

    List<Enrollments> findByIdIn(List<Long> ids);

    List<Enrollments> findAllByUserId(Long userId);

    Enrollments save(Enrollments enrollment);

    @Modifying
    @Transactional
    @Query("DELETE FROM Enrollments e WHERE e.user.id = :userId AND e.course.id = :courseId")
    void deleteByCourseIdAndUserId(@Param("courseId") Long courseId, @Param("userId") Long userId);
}
