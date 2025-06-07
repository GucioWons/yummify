package com.guciowons.yummify.auth.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecurePasswordGeneratorTest {
    private final SecurePasswordGenerator underTest = new SecurePasswordGenerator();

    @Test
    void shouldGenerateSecurePassword() {
        int length = 12;

        String password = underTest.generate(length);

        assertNotNull(password);
        assertEquals(length, password.length());

        long upperCount = password.chars().filter(Character::isUpperCase).count();
        long lowerCount = password.chars().filter(Character::isLowerCase).count();
        long digitCount = password.chars().filter(Character::isDigit).count();

        assertTrue(upperCount >= 2, "Password should contain at least 2 uppercase letters");
        assertTrue(lowerCount >= 2, "Password should contain at least 2 lowercase letters");
        assertTrue(digitCount >= 2, "Password should contain at least 2 digits");
    }
}
