package com.awbd.domain;

import com.awbd.entities.Courses;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("h2")
public class EntityManagerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findCourse(){
        System.out.println(entityManager.getEntityManagerFactory());
        Courses courseFound = entityManager.find(Courses.class, 1L);
        assertEquals(courseFound.getTitle(), "Java Programming");
    }
}
