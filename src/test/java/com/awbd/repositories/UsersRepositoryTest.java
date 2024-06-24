package com.awbd.repositories;

import com.awbd.entities.Users;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class UsersRepositoryTest {

    UsersRepository usersRepository;

    @Autowired
    UsersRepositoryTest(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Test
    public void findByName() {
        List<Users> users = usersRepository.findByUsernameLike("%oh%");
        Assertions.assertFalse(users.isEmpty());
        log.info("findByLastNameLike ...");
        users.forEach(user -> log.info(user.getUsername()));
    }

    @Test public void findByIds() {
        List<Users> users = usersRepository.findByIdIn(Arrays.asList(1L,2L));
        Assertions.assertFalse(users.isEmpty());
        log.info("findByIds ...");
        users.forEach(user -> log.info(user.getUsername()));
    }
}
