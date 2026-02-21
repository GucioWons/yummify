package com.guciowons.yummify.auth.domain.port.out;

import com.guciowons.yummify.auth.domain.model.Password;

public interface PasswordGeneratorPort {
    Password generate(int length, int upperCaseMin, int lowerCaseMin, int digitMin);
}
