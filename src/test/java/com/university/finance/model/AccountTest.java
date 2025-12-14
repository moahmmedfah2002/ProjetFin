package com.university.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccountCreationAndGetters() {
        Account account = new Account("user1", 1000.0);

        assertEquals("user1", account.getUsername());
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    void testSetBalance() {
        Account account = new Account("user1", 100.0);
        account.setBalance(250.0);

        assertEquals(250.0, account.getBalance());
    }

    @Test
    void testToString() {
        Account account = new Account("user1", 100.0);
        String stringRes = account.toString();

        assertTrue(stringRes.contains("user1"));
        assertTrue(stringRes.contains("100.0"));
    }
}