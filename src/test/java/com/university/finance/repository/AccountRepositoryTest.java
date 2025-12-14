package com.university.finance.repository;

import com.university.finance.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepository();
    }

    @Test
    void testSaveAndFindAccount() {
        String username = "nouveauUser";
        Account newAccount = new Account(username, 200.0);

        accountRepository.save(newAccount);

        Account retrieved = accountRepository.findByUsername(username);

        assertNotNull(retrieved, "Le compte récupéré ne doit pas être null");
        assertEquals(username, retrieved.getUsername());
        assertEquals(200.0, retrieved.getBalance());
    }

    @Test
    void testUpdateAccountBalance() {
        Account account = new Account("user1", 100.0);
        accountRepository.save(account);

        account.setBalance(500.0);
        accountRepository.save(account);

        Account updated = accountRepository.findByUsername("user1");
        assertEquals(500.0, updated.getBalance(), "Le solde devrait être mis à jour");
    }

    @Test
    void testFindByUnknownUsername() {
        Account result = accountRepository.findByUsername("inconnu");
        assertNull(result, "Doit retourner null pour un utilisateur inexistant");
    }
}