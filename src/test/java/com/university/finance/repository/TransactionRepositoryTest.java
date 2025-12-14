package com.university.finance.repository;

import com.university.finance.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    void testSaveAndRetrieveTransactions() {
        String username = "userTest";
        Transaction t1 = new Transaction(username, "DEPOSIT", 100.0, "Dépôt initial");
        Transaction t2 = new Transaction(username, "WITHDRAWAL", 50.0, "Retrait");

        transactionRepository.save(t1);
        transactionRepository.save(t2);

        List<Transaction> history = transactionRepository.findByUsername(username);

        assertNotNull(history);
        assertEquals(2, history.size(), "Il devrait y avoir 2 transactions");
        assertEquals("DEPOSIT", history.get(0).getType());
        assertEquals("WITHDRAWAL", history.get(1).getType());
    }

    @Test
    void testFindByUsernameNoTransactions() {

        List<Transaction> history = transactionRepository.findByUsername("jamaisVu");

        assertNotNull(history, "Ne doit pas retourner null même si aucune transaction");
        assertTrue(history.isEmpty(), "La liste doit être vide");
    }

    @Test
    void testTransactionDataIntegrity() {
        Transaction t = new Transaction("userA", "TRANSFER", 300.0, "Vers userB");
        transactionRepository.save(t);

        Transaction retrieved = transactionRepository.findByUsername("userA").get(0);

        assertEquals(300.0, retrieved.getAmount());
        assertNotNull(retrieved.getTimestamp(), "Le timestamp doit être généré automatiquement");
    }
}