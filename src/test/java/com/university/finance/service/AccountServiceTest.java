package com.university.finance.service;

import com.university.finance.exception.AccountNotFoundException;
import com.university.finance.exception.InsufficientFundsException;
import com.university.finance.model.Account;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService accountService;
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepository();
        transactionRepository = new TransactionRepository();
        accountService = new AccountService(accountRepository, transactionRepository);

        accountRepository.save(new Account("testUser", 100.0));
    }

    @Test
    void testDepositSuccess() {
        accountService.deposit("testUser", 50.0);
        Account account = accountRepository.findByUsername("testUser");
        assertEquals(150.0, account.getBalance(), "Le solde devrait être 150.0 après dépôt");
    }

    @Test
    void testWithdrawSuccess() {
        accountService.withdraw("testUser", 40.0);
        Account account = accountRepository.findByUsername("testUser");
        assertEquals(60.0, account.getBalance(), "Le solde devrait être 60.0 après retrait");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        assertThrows(InsufficientFundsException.class, () -> {
            accountService.withdraw("testUser", 200.0);
        });
    }

    @Test
    void testAccountNotFound() {
        assertThrows(AccountNotFoundException.class, () -> {
            accountService.deposit("unknownUser", 50.0);
        });
    }

    @Test
    void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> {
            accountService.deposit("testUser", -50.0);
        });
    }
}