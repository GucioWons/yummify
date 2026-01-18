package com.guciowons.yummify.auth.application.fixture;

import com.guciowons.yummify.auth.application.model.CreateUserCommand;
import com.guciowons.yummify.auth.application.model.GenerateOtpCommand;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;

public class AuthApplicationFixture {
    public static CreateUserCommand givenCreateUserCommand(boolean withPassword) {
        return new CreateUserCommand(
                givenEmail(),
                givenUsername(),
                givenPersonalData(),
                givenRestaurantId(),
                withPassword
        );
    }

    public static GenerateOtpCommand givenGenerateOtpCommand() {
        return new GenerateOtpCommand(givenUserId());
    }
}
