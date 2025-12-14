package com.university.finance.pattern.observer;

import com.university.finance.util.DateUtils;

public class AuditLogger implements TransactionObserver {
    @Override
    public void update(String message) {
        System.out.println(DateUtils.getCurrentTimestamp() + " [AUDIT_LOG] : " + message);
    }
}