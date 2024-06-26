package com.awbd.repositories;

import com.awbd.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Optional<Authority> findById(Long id);

}