package com.university.finance.pattern.observer;

import com.university.finance.util.DateUtils;


public class NotificationService implements TransactionObserver {

    @Override
    public void update(String message) {
        String timestamp = DateUtils.getCurrentTimestamp();
        System.out.println(timestamp + " [NOTIFICATION_SERVICE] : " + message + " (Notification envoyée à l'utilisateur)");

    }
}