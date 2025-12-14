package com.university.finance.controller;

import com.university.finance.service.BankingService;
import com.university.finance.service.TransactionService;

public class TransactionController {
    private final BankingService bankingService;
    private final TransactionService transactionService;

    public TransactionController(BankingService bankingService, TransactionService transactionService) {
        this.bankingService = bankingService;
        this.transactionService = transactionService;
    }

    public void handleDeposit(String username, double amount) {
        try {
            bankingService.performDeposit(username, amount);
            System.out.println(" Dépôt réussi!");
        } catch (Exception e) {
            System.err.println(" Échec du dépôt : " + e.getMessage());
        }
    }

    public void handleWithdrawal(String username, double amount) {
        try {
            bankingService.performWithdrawal(username, amount);
            System.out.println(" Retrait réussi!");
        } catch (Exception e) {
            System.err.println(" Échec du retrait : " + e.getMessage());
        }
    }

    public void handleTransfer(String fromUser, String toUser, double amount) {
        try {
            bankingService.transfer(fromUser, toUser, amount);
            System.out.println(" Transfert réussi!");
        } catch (Exception e) {
            System.err.println(" Échec du transfert : " + e.getMessage());
        }
    }

    public void displayHistory(String username) {
        System.out.println(" Historique de " + username + " :");
        transactionService.getHistory(username).forEach(System.out::println);
    }
}