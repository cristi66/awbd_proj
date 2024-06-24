package com.awbd.domain;

import com.awbd.entities.Users;
import com.awbd.services.PersistenceContextExtended;
import com.awbd.services.PersistenceContextTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TransactionRequiredException;
import org.h2.tools.Server;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.awbd.AwbdApplication.class)
@ActiveProfiles("h2")
public class PersistenceContextTest {

    @Autowired
    PersistenceContextExtended persistenceContextExtended;

    @Autowired
    PersistenceContextTransaction persistenceContextTransction;

    @Test(expected = TransactionRequiredException.class)
    public void persistenceContextTransctionThrowException() {
        persistenceContextTransction.update(1L, "William");
    }

    @Test
    public void persistenceContextTransctionExtended() {
        persistenceContextTransction.updateInTransaction(1L, "William");
        Users userExtended = persistenceContextExtended.find(1L);
        System.out.println(userExtended.getUsername());
        assertEquals(userExtended.getUsername(), "William");
    }

    @Test
    public void persistenceContextExtendedExtended() {
        persistenceContextExtended.update(1L, "Snow");
        Users userExtended = persistenceContextExtended.find(1L);
        System.out.println(userExtended.getUsername());
        assertEquals(userExtended.getUsername(), "Snow");
    }

    @Test
    public void persistenceContextExtendedTransaction() {
        persistenceContextExtended.update(1L, "Will");
        Users userTransaction = persistenceContextTransction.find(1L);
        System.out.println(userTransaction.getUsername());
        assertNotEquals(userTransaction.getUsername(), "Will");
    }
}
