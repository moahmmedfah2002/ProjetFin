package com.university.finance.observer;

import com.university.finance.pattern.observer.TransactionObserver;
import com.university.finance.pattern.observer.TransactionSubject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionSubjectTest {

    static class MockObserver implements TransactionObserver {
        String receivedMessage = null;
        int callCount = 0;

        @Override
        public void update(String message) {
            this.receivedMessage = message;
            this.callCount++;
        }
    }

    static class ConcreteSubject extends TransactionSubject {
        public void triggerEvent(String message) {
            notifyObservers(message);
        }
    }

    @Test
    void testAttachAndNotify() {
        ConcreteSubject subject = new ConcreteSubject();
        MockObserver observer1 = new MockObserver();
        MockObserver observer2 = new MockObserver();

        subject.attach(observer1);
        subject.attach(observer2);

        String msg = "Event Test";
        subject.triggerEvent(msg);

        assertEquals(msg, observer1.receivedMessage, "Obs1 doit recevoir le message");
        assertEquals(1, observer1.callCount);

        assertEquals(msg, observer2.receivedMessage, "Obs2 doit recevoir le message");
        assertEquals(1, observer2.callCount);
    }

    @Test
    void testNoDuplicateObservers() {
        ConcreteSubject subject = new ConcreteSubject();
        MockObserver observer = new MockObserver();

        subject.attach(observer);
        subject.attach(observer);

        subject.triggerEvent("Hello");


        assertEquals(1, observer.callCount, "L'observateur ne doit pas être notifié en double");
    }
}