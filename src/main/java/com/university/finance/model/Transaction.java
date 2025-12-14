package com.university.finance.model;

import java.time.LocalDateTime;

public class Transaction {
    private String accountUsername;
    private String type;
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(String accountUsername, String type, double amount, String description) {
        this.accountUsername = accountUsername;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public String getAccountUsername() { return accountUsername; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return timestamp.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
             + " [" + type + "] " + description + " : " + (type.equals("DEPOSIT") ? "+" : "-") + amount;
    }
}