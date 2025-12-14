package com.university.finance.pattern.factory;

import com.university.finance.model.Account;

public class AccountFactory {

    public Account createAccount(String username, double initialDeposit) {
        return new Account(username, initialDeposit);
    }
}