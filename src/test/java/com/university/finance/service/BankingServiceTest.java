package com.university.finance.service;

import com.university.finance.exception.InsufficientFundsException;
import com.university.finance.model.Account;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankingServiceTest {

    private BankingService bankingService;
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        AccountService accountService = new AccountService(accountRepository, transactionRepository);

        bankingService = new BankingService(accountService, accountRepository, transactionRepository);

        accountRepository.save(new Account("Alice", 1000.0));
        accountRepository.save(new Account("Bob", 500.0));
    }

    @Test
    void testTransferSuccess() {
        bankingService.transfer("Alice", "Bob", 200.0);

        Account alice = accountRepository.findByUsername("Alice");
        Account bob = accountRepository.findByUsername("Bob");

        assertEquals(800.0, alice.getBalance(), "Le solde d'Alice doit être débité");
        assertEquals(700.0, bob.getBalance(), "Le solde de Bob doit être crédité");
    }

    @Test
    void testTransferInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> {
            bankingService.transfer("Alice", "Bob", 2000.0);
        });
    }

    @Test
    void testTransferToUnknownUser() {
        assertThrows(RuntimeException.class, () -> {
            bankingService.transfer("Alice", "Unknown", 100.0);
        });
    }
}