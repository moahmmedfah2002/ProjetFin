package com.university.finance.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransactionCreation() {
        String user = "bob";
        double amount = 50.0;
        String type = "DEPOSIT";
        String desc = "Test depot";

        Transaction t = new Transaction(user, type, amount, desc);

        assertEquals(user, t.getAccountUsername());
        assertEquals(type, t.getType());
        assertEquals(amount, t.getAmount());
        assertEquals(desc, t.getDescription());

        assertNotNull(t.getTimestamp());
        assertTrue(t.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(t.getTimestamp().isAfter(LocalDateTime.now().minusSeconds(5)));
    }

    @Test
    void testToStringFormat() {
        Transaction t = new Transaction("bob", "WITHDRAWAL", 20.0, "Retrait");
        String result = t.toString();



        assertNotNull(result);
        assertTrue(result.contains("[WITHDRAWAL]"));
        assertTrue(result.contains("Retrait"));

        assertTrue(result.contains("-20.0"));
    }
}