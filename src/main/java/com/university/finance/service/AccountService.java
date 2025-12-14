package com.university.finance.service;

import com.university.finance.exception.AccountNotFoundException;
import com.university.finance.model.Account;
import com.university.finance.model.Transaction;
import com.university.finance.pattern.strategy.DepositStrategy;
import com.university.finance.pattern.strategy.WithdrawStrategy;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.TransactionRepository;

public class AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final DepositStrategy depositStrategy = new DepositStrategy();
    private final WithdrawStrategy withdrawStrategy = new WithdrawStrategy();

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account findAccount(String username) {
        return accountRepository.findByUsername(username);
    }

    public void deposit(String username, double amount) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new AccountNotFoundException(username);
        }

        String type = depositStrategy.execute(account, amount);

        accountRepository.save(account);

        Transaction t = new Transaction(username, type, amount, "Dépôt en espèces/chèque");
        transactionRepository.save(t);
    }

    public void withdraw(String username, double amount) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new AccountNotFoundException(username);
        }

        String type = withdrawStrategy.execute(account, amount);

        accountRepository.save(account);

        Transaction t = new Transaction(username, type, amount, "Retrait en espèces");
        transactionRepository.save(t);
    }
}