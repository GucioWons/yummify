package com.guciowons.yummify.auth.domain.port.out;

public interface PasswordGeneratorPort {
    String generate(int length, int upperCaseMin, int lowerCaseMin, int digitMin);
}
