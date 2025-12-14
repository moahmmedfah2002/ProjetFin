package com.university.finance.controller;

import com.university.finance.model.Account;
import com.university.finance.service.AccountService;

public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public void displayBalance(String username) {
        try {
            Account account = accountService.findAccount(username);
            if (account != null) {
                System.out.println(" Solde de " + username + " : " + account.getBalance());
            } else {
                System.out.println("Utilisateur/Compte non trouvé!");
            }
        } catch (Exception e) {
            System.err.println("Erreur de consultation: " + e.getMessage());
        }
    }
}