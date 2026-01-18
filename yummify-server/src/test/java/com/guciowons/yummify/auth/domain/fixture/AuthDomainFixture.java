package com.guciowons.yummify.auth.domain.fixture;

import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.auth.domain.model.value.*;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class AuthDomainFixture {
    public static User givenUser(boolean withPassword) {
        return User.of(
                givenEmail(),
                givenUsername(),
                givenPersonalData(),
                givenRestaurantId(),
                withPassword ? givenPassword() : null
        );
    }

    public static Otp givenOtp() {
        return Otp.of("otp", LocalDateTime.now().plus(Duration.ofMinutes(5)));
    }

    public static Email givenEmail() {
        return Email.of("test@example.com");
    }

    public static Username givenUsername() {
        return Username.of("username");
    }

    public static PersonalData givenPersonalData() {
        return PersonalData.of("firstName", "lastName");
    }

    public static RestaurantId givenRestaurantId() {
        return RestaurantId.of(UUID.randomUUID());
    }

    public static Password givenPassword() {
        return Password.of("password");
    }

    public static UserId givenUserId() {
        return UserId.of(UUID.randomUUID().toString());
    }


}
