package com.guciowons.yummify.auth.infrastructure.out.passay;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PassayPasswordGeneratorAdapterTest {
    private final PassayPasswordGeneratorAdapter underTest = new PassayPasswordGeneratorAdapter();

    @Test
    void shouldGeneratePasswordWithCorrectLengthAndCharacterTypes() {
        // given
        int length = 12;
        int upperCaseMin = 2;
        int lowerCaseMin = 3;
        int digitMin = 2;

        // when
        String password = underTest.generate(length, upperCaseMin, lowerCaseMin, digitMin);

        // then
        long upperCount = password.chars()
                .filter(Character::isUpperCase)
                .count();
        long lowerCount = password.chars()
                .filter(Character::isLowerCase)
                .count();
        long digitCount = password.chars()
                .filter(Character::isDigit)
                .count();

        assertThat(upperCount).isGreaterThanOrEqualTo(upperCaseMin);
        assertThat(lowerCount).isGreaterThanOrEqualTo(lowerCaseMin);
        assertThat(digitCount).isGreaterThanOrEqualTo(digitMin);
    }

    @Test
    void shouldGenerateDifferentPasswords() {
        // given
        int length = 12;
        int upperCaseMin = 2;
        int lowerCaseMin = 3;
        int digitMin = 2;

        // when
        String password1 = underTest.generate(length, upperCaseMin, lowerCaseMin, digitMin);
        String password2 = underTest.generate(length, upperCaseMin, lowerCaseMin, digitMin);

        // then
        assertThat(password1).isNotEqualTo(password2);
    }

}