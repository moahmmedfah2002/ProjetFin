package com.university.finance.controller;

import com.university.finance.service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void registerNewUser(String username, String password, double initialDeposit) {
        try {
            userService.registerUser(username, password, initialDeposit);
            System.out.println(" Utilisateur " + username + " créé avec succès.");
        } catch (Exception e) {
            System.err.println(" Erreur lors de l'inscription : " + e.getMessage());
        }
    }
}