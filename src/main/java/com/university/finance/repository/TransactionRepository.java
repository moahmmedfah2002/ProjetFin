package com.university.finance.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.university.finance.model.Transaction;

public class TransactionRepository {
    private final Map<String, List<Transaction>> userTransactions = new HashMap<>();

    public void save(Transaction transaction) {
        String username = transaction.getAccountUsername();
        userTransactions.computeIfAbsent(username, k -> new ArrayList<>()).add(transaction);
    }

    public List<Transaction> findByUsername(String username) {
        return userTransactions.getOrDefault(username, new ArrayList<>());
    }
}