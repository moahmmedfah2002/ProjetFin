package com.university.finance.pattern.factory;

import com.university.finance.model.User;

public abstract class UserFactory {
    public abstract User createUser(String username, String password);
}