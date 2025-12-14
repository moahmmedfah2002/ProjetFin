package com.university.finance.model;

public class Account {
    private String username;
    private double balance;

    public Account(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{username='" + username + "', balance=" + balance + "}";
    }
}