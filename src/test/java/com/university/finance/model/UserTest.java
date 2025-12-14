package com.university.finance.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User("alice", "password123");

        assertEquals("alice", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testSetPassword() {
        User user = new User("alice", "oldPass");
        user.setPassword("newPass");

        assertEquals("newPass", user.getPassword());
    }
}