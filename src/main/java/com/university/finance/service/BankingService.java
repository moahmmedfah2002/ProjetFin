package com.university.finance.service;

import com.university.finance.exception.AccountNotFoundException;
import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.pattern.observer.TransactionSubject;
import com.university.finance.pattern.strategy.TransferStrategy;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.TransactionRepository;

public class BankingService extends TransactionSubject {
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final TransferStrategy transferStrategy = new TransferStrategy();

    public BankingService(AccountService accountService, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void performDeposit(String username, double amount) {
        accountService.deposit(username, amount);
        notifyObservers("DEPOT réussi pour " + username + ": +" + amount);
    }

    public void performWithdrawal(String username, double amount) {
        accountService.withdraw(username, amount);
        notifyObservers("RETRAIT réussi pour " + username + ": -" + amount);
    }

    public void transfer(String fromUser, String toUser, double amount) {
        Account fromAccount = accountRepository.findByUsername(fromUser);
        Account toAccount = accountRepository.findByUsername(toUser);

        if (fromAccount == null) {
            throw new AccountNotFoundException(fromUser);
        }
        if (toAccount == null) {
            throw new AccountNotFoundException(toUser);
        }

        String type = transferStrategy.execute(fromAccount, amount, toAccount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        transactionRepository.save(new Transaction(fromUser, type, amount, "Transfert vers " + toUser));
        transactionRepository.save(new Transaction(toUser, "DEPOSIT", amount, "Transfert de " + fromUser));

        notifyObservers("TRANSFERT de " + fromUser + " à " + toUser + ": " + amount);
    }
}