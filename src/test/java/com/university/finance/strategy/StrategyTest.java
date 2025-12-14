package com.university.finance.strategy;

import com.university.finance.model.Account;
import com.university.finance.pattern.strategy.DepositStrategy;
import com.university.finance.pattern.strategy.TransferStrategy;
import com.university.finance.pattern.strategy.WithdrawStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    @Test
    void testDepositStrategy() {
        Account acc = new Account("test", 100.0);
        DepositStrategy strategy = new DepositStrategy();

        String result = strategy.execute(acc, 50.0);

        assertEquals(150.0, acc.getBalance());
        assertEquals("DEPOSIT", result);
    }

    @Test
    void testWithdrawStrategy() {
        Account acc = new Account("test", 100.0);
        WithdrawStrategy strategy = new WithdrawStrategy();

        String result = strategy.execute(acc, 30.0);

        assertEquals(70.0, acc.getBalance());
        assertEquals("WITHDRAWAL", result);
    }

    @Test
    void testTransferStrategy() {
        Account from = new Account("A", 100.0);
        Account to = new Account("B", 0.0);
        TransferStrategy strategy = new TransferStrategy();

        String result = strategy.execute(from, 50.0, to);

        assertEquals(50.0, from.getBalance());
        assertEquals(50.0, to.getBalance());
        assertEquals("TRANSFER", result);
    }
}