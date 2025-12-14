package com.university.finance.pattern.strategy;

import com.university.finance.model.Account;
import com.university.finance.exception.InsufficientFundsException;
import com.university.finance.exception.AccountNotFoundException;

public class TransferStrategy implements TransactionStrategy {

    @Override
    public String execute(Account fromAccount, double amount, Object... args) {
        if (args.length < 1 || !(args[0] instanceof Account)) {
            throw new IllegalArgumentException("Le TransferStrategy nécessite un compte destinataire (toAccount).");
        }
        Account toAccount = (Account) args[0];

        if (fromAccount == null || toAccount == null) {

            if (fromAccount == null) throw new AccountNotFoundException("Compte source non trouvé.");
            if (toAccount == null) throw new AccountNotFoundException("Compte destinataire non trouvé.");
        }

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException(fromAccount.getUsername(), amount, fromAccount.getBalance());
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        return "TRANSFER";
    }
}