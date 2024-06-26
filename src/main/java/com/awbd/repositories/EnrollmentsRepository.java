package com.awbd.repositories;

import com.awbd.dtos.EnrollmentsDTO;
import com.awbd.entities.Enrollments;
import com.awbd.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentsRepository extends JpaRepository<Enrollments, Long> {

    List<Enrollments> findByIdIn(List<Long> ids);

    List<Enrollments> findAllByUserId(Long userId);

    Enrollments save(Enrollments enrollment);
}
