package com.university.finance.pattern.factory;

import com.university.finance.model.User;

public class ConcreteUserFactory extends UserFactory {

    @Override
    public User createUser(String username, String password) {
        return new User(username, password);
    }
}