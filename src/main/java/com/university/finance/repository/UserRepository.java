package com.university.finance.repository;

import com.university.finance.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepository() {
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));
    }

    public User findByUsername(String username) {
        return users.get(username);
    }

    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }
}