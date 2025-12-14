package com.university.finance.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String username) {
        super("Compte non trouvé pour l'utilisateur: " + username);
    }
}