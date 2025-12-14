package com.university.finance.pattern.observer;

public interface TransactionObserver {
    void update(String message);
}