package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.exception.InsufficientFundsException;

public class WithdrawStrategy implements TransactionStrategy {

    @Override
    public String execute(Account account, double amount, Object... args) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif.");
        }

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException(account.getUsername(), amount, account.getBalance());
        }

        account.setBalance(account.getBalance() - amount);

        return "WITHDRAWAL";
    }
}