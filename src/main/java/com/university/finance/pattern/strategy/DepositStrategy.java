package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;

public class DepositStrategy implements TransactionStrategy {

    @Override
    public String execute(Account account, double amount, Object... args) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif.");
        }

        account.setBalance(account.getBalance() + amount);

        return "DEPOSIT";
    }
}