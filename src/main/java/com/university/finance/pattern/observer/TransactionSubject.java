package com.university.finance.pattern.observer;

import java.util.ArrayList;
import java.util.List;


public abstract class TransactionSubject {
    
    private final List<TransactionObserver> observers = new ArrayList<>();


    public void attach(TransactionObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }


    public void detach(TransactionObserver observer) {
        observers.remove(observer);
    }


    protected void notifyObservers(String message) {
        for (TransactionObserver observer : observers) {
            observer.update(message);
        }
    }
}