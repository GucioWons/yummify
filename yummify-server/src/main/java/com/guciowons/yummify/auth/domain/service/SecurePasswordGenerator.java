package com.guciowons.yummify.auth.domain.service;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SecurePasswordGenerator {
    private final PasswordGenerator generator = new PasswordGenerator();
    private final List<CharacterRule> rules = Arrays.asList(
            new CharacterRule(EnglishCharacterData.UpperCase, 2),
            new CharacterRule(EnglishCharacterData.LowerCase, 2),
            new CharacterRule(EnglishCharacterData.Digit, 2)
    );

    public String generate(int length) {
        return generator.generatePassword(length, rules);
    }
}
