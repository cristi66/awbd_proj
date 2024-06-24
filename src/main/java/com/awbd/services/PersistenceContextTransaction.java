package com.awbd.services;

import com.awbd.entities.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PersistenceContextTransaction {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Users updateInTransaction(Long id, String name) {
        Users updatedUser = entityManager.find(Users.class, id);
        updatedUser.setUsername(name);
        entityManager.persist(updatedUser);
        return updatedUser;
    }

    public Users update(Long id, String name) {
        Users updatedUser = entityManager.find(Users.class, id);
        updatedUser.setUsername(name);
        entityManager.persist(updatedUser);
        return updatedUser;
    }

    public Users find(long id) {
        return entityManager.find(Users.class, id);
    }
}
