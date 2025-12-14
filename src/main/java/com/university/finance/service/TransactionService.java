package com.university.finance.service;

import com.university.finance.model.Transaction;
import com.university.finance.repository.TransactionRepository;
import java.util.List;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getHistory(String username) {
        return transactionRepository.findByUsername(username);
    }
}