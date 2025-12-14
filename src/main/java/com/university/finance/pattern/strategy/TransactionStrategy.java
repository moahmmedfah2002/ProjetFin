package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;


public interface TransactionStrategy {


    String execute(Account account, double amount, Object... args);
}