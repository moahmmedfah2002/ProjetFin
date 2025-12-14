package com.university.finance.service;

import com.university.finance.model.Account;
import com.university.finance.model.User;
import com.university.finance.pattern.factory.AccountFactory;
import com.university.finance.pattern.factory.UserFactory;
import com.university.finance.pattern.factory.ConcreteUserFactory;
import com.university.finance.repository.AccountRepository;
import com.university.finance.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    private final UserFactory userFactory;
    private final AccountFactory accountFactory;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;

        // Utilisation de l'implémentation concrète de UserFactory
        this.userFactory = new ConcreteUserFactory();
        this.accountFactory = new AccountFactory();
    }

    public void registerUser(String username, String password, double initialDeposit) {
        if (userRepository.exists(username)) {
            throw new RuntimeException("L'utilisateur existe déjà.");
        }

        User newUser = userFactory.createUser(username, password);
        Account newAccount = accountFactory.createAccount(username, initialDeposit);

        userRepository.save(newUser);
        accountRepository.save(newAccount);
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username);
    }
}