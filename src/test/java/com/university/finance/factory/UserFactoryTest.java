package com.university.finance.factory;

import com.university.finance.model.User;
import com.university.finance.pattern.factory.ConcreteUserFactory;
import com.university.finance.pattern.factory.UserFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void testCreateUser() {

        UserFactory factory = new ConcreteUserFactory();

        String username = "factoryUser";
        String password = "factoryPassword";

        User user = factory.createUser(username, password);

        assertNotNull(user, "La factory ne doit pas retourner null");
        assertEquals(username, user.getUsername(), "Le nom d'utilisateur doit correspondre");
        assertEquals(password, user.getPassword(), "Le mot de passe doit correspondre");

        assertTrue(user instanceof User);
    }
}