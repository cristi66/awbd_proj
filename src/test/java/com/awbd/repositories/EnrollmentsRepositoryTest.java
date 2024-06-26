package com.awbd.repositories;

import com.awbd.entities.Enrollments;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class EnrollmentsRepositoryTest {

    EnrollmentsRepository enrollmentsRepository;

    @Autowired
    public EnrollmentsRepositoryTest(EnrollmentsRepository enrollmentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
    }

    @Test
    public void findAllByUserId(){
        List<Enrollments> enrollmentsList = enrollmentsRepository.findAllByUserId(1L);
        Assertions.assertEquals(2, enrollmentsList.size());
    }
}
