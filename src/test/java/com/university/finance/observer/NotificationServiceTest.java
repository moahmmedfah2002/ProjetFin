package com.university.finance.observer;

import com.university.finance.pattern.observer.NotificationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NotificationServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testUpdateSendsNotification() {
        NotificationService service = new NotificationService();
        String message = "Virement reçu";

        service.update(message);

        String output = outContent.toString();
        assertTrue(output.contains("[NOTIFICATION_SERVICE]"));
        assertTrue(output.contains(message));
    }
}