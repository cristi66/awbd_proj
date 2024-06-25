package com.awbd.repositories;

import com.awbd.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);

    List<Users> findByUsernameLike(String name);

    List<Users> findByIdIn(List<Long> ids);

    Optional<Users> findById(Long id);

    Users save(Users user);
}
