package com.university.finance.repository;

import com.university.finance.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void testSaveAndFindUser() {
        User user = new User("alice", "secret123");

        userRepository.save(user);

        User retrieved = userRepository.findByUsername("alice");

        assertNotNull(retrieved);
        assertEquals("secret123", retrieved.getPassword());
    }

    @Test
    void testExists() {
        assertTrue(userRepository.exists("user1"), "user1 devrait exister par défaut");

        userRepository.save(new User("bob", "pass"));
        assertTrue(userRepository.exists("bob"), "bob devrait exister après sauvegarde");

        assertFalse(userRepository.exists("fantome"), "Un utilisateur non créé ne doit pas exister");
    }
}