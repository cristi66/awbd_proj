package com.awbd.repositories;

import com.awbd.entities.Courses;
import com.awbd.enums.CourseTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class CoursesRepositoryTest {

    CoursesRepository coursesRepository;

    @Autowired
    public CoursesRepositoryTest(CoursesRepository coursesRepository) {
        this.coursesRepository = coursesRepository;
    }

    @Test
    public void findById(){
        Optional<Courses> courseOptional = coursesRepository.findById(1L);
        Assertions.assertFalse(courseOptional.isEmpty());
        log.info("Finding course by id.." + courseOptional.get().getId());
    }

    @Test
    public void findAllByType(){
        List<Courses> coursesList = coursesRepository.findAllByType(CourseTypeEnum.SELECT);
        Assertions.assertFalse(coursesList.isEmpty());
        Assertions.assertEquals(1, coursesList.size());
        log.info("Finding all courses by type" + coursesList.get(0).getType());
    }

    @Test
    public void findByTitle(){
        Optional<Courses> courseOptional = coursesRepository.findByTitle("Java Programming");
        Assertions.assertFalse(courseOptional.isEmpty());
        log.info("Finding course by title.." + courseOptional.get().getTitle());
    }

}
