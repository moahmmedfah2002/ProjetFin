package com.university.finance.service;

import com.university.finance.model.User;
import com.university.finance.model.Account;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        accountRepository = new AccountRepository();
        userService = new UserService(userRepository, accountRepository);
    }

    @Test
    void testRegisterUserSuccess() {
        userService.registerUser("newUser", "password123", 500.0);

        User user = userRepository.findByUsername("newUser");
        Account account = accountRepository.findByUsername("newUser");

        assertNotNull(user, "L'utilisateur doit être créé");
        assertEquals("password123", user.getPassword());

        assertNotNull(account, "Le compte doit être créé");
        assertEquals(500.0, account.getBalance());
    }

    @Test
    void testRegisterDuplicateUser() {
        userService.registerUser("duplicate", "pass", 100.0);

        assertThrows(RuntimeException.class, () -> {
            userService.registerUser("duplicate", "pass", 100.0);
        });
    }
}