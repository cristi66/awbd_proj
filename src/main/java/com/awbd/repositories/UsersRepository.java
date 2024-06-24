package com.awbd.repositories;

import com.awbd.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
