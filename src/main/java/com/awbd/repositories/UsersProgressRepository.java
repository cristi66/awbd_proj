package com.awbd.repositories;

import com.awbd.entities.UsersProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsersProgressRepository extends JpaRepository<UsersProgress, Long> {
}
