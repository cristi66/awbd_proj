package com.awbd.domain;

import com.awbd.entities.Users;
import com.awbd.services.PersistenceContextExtended;
import com.awbd.services.PersistenceContextTransaction;
import jakarta.persistence.TransactionRequiredException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.awbd.AwbdApplication.class)
@ActiveProfiles("h2")
public class PersistenceContextTest {

    @Autowired
    PersistenceContextExtended persistenceContextExtended;

    @Autowired
    PersistenceContextTransaction persistenceContextTransaction;

    @Test(expected = TransactionRequiredException.class)
    public void persistenceContextTransactionThrowException() {
        persistenceContextTransaction.update(1L, "William");
    }

    @Test
    public void persistenceContextTransactionExtended() {
        persistenceContextTransaction.updateInTransaction(1L, "William");
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
        Users userTransaction = persistenceContextTransaction.find(1L);
        System.out.println(userTransaction.getUsername());
        assertNotEquals(userTransaction.getUsername(), "Will");
    }
}
