package com.university.finance.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String username, double requiredAmount, double currentBalance) {
        super("Fonds insuffisants pour " + username + ". Solde actuel: " + currentBalance + ", Requis: " + requiredAmount);
    }
}