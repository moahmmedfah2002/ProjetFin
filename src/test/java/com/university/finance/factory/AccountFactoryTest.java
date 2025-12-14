package com.university.finance.factory;

import com.university.finance.model.Account;
import com.university.finance.pattern.factory.AccountFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountFactoryTest {

    @Test
    void testCreateAccount() {
        AccountFactory factory = new AccountFactory();

        String username = "accountUser";
        double initialBalance = 1000.0;

        Account account = factory.createAccount(username, initialBalance);

        assertNotNull(account, "La factory ne doit pas retourner null");
        assertEquals(username, account.getUsername());
        assertEquals(initialBalance, account.getBalance());
    }

    @Test
    void testCreateAccountWithZeroBalance() {
        AccountFactory factory = new AccountFactory();
        Account account = factory.createAccount("zeroBalanceUser", 0.0);

        assertEquals(0.0, account.getBalance());
    }
}