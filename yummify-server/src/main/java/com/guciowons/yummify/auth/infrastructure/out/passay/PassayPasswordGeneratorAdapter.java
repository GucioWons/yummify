package com.guciowons.yummify.auth.infrastructure.out.passay;

import com.guciowons.yummify.auth.domain.port.out.PasswordGeneratorPort;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PassayPasswordGeneratorAdapter implements PasswordGeneratorPort {
    private final PasswordGenerator generator = new PasswordGenerator();

    @Override
    public String generate(int length, int upperCaseMin, int lowerCaseMin, int digitMin) {
        List<CharacterRule> rules = Arrays.asList(
                new CharacterRule(EnglishCharacterData.UpperCase, upperCaseMin),
                new CharacterRule(EnglishCharacterData.LowerCase, lowerCaseMin),
                new CharacterRule(EnglishCharacterData.Digit, digitMin)
        );

        return generator.generatePassword(length, rules);
    }
}
