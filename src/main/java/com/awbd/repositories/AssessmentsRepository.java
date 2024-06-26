package com.awbd.repositories;

import com.awbd.entities.Assessments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssessmentsRepository extends JpaRepository<Assessments, Integer> {
    Optional<Assessments> findById(Long id);

}
