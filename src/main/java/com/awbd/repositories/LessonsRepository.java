package com.awbd.repositories;

import com.awbd.entities.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepository extends JpaRepository<Lessons, Integer> {
}
