package com.awbd.services;

import com.awbd.entities.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PersistenceContextExtended {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Transactional
    public Users updateInTransaction(Long id, String name) {
        Users updatedUser = entityManager.find(Users.class, id);
        updatedUser.setUsername(name);
        entityManager.persist(updatedUser);
        return updatedUser;
    }
    public Users update(Long participantId, String name) {
        Users updatedParticipant = entityManager.find(Users.class, participantId);
        updatedParticipant.setUsername(name);
        entityManager.persist(updatedParticipant);
        return updatedParticipant;
    }
    public Users find(long id) {

        return entityManager.find(Users.class, id);
    }
}