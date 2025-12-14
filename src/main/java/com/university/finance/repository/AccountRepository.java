package com.university.finance.repository;

import com.university.finance.model.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();

    public AccountRepository() {
        accounts.put("user1", new Account("user1", 1000.0));
        accounts.put("user2", new Account("user2", 500.0));
    }

    public Account findByUsername(String username) {
        return accounts.get(username);
    }

    public void save(Account account) {
        accounts.put(account.getUsername(), account);
    }
}