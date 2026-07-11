package com.guciowons.yummify.auth.domain.fixture;

import com.guciowons.yummify.auth.domain.model.Otp;
import com.guciowons.yummify.auth.domain.model.Password;
import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.model.User;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.security.domain.Permission;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthDomainFixture {
    public static User givenUser(boolean withPassword) {
        return User.create(
                givenUserRestaurantId(),
                givenUserEmail(),
                givenUserUsername(),
                givenUserPersonalData(),
                withPassword ? givenPassword() : null
        );
    }

    public static Otp givenOtp() {
        return Otp.of(givenPassword(), LocalDateTime.now().plus(Duration.ofMinutes(5)));
    }

    public static User.RestaurantId givenUserRestaurantId() {
        return User.RestaurantId.of(UUID.nameUUIDFromBytes("restaurant".getBytes()));
    }

    public static User.ExternalId givenUserExternalId(int seed) {
        return User.ExternalId.of(UUID.nameUUIDFromBytes("user-%s".formatted(seed).getBytes()).toString());
    }


    public static User.Email givenUserEmail() {
        return User.Email.of("test@example.com");
    }

    public static User.Username givenUserUsername() {
        return User.Username.of("username");
    }

    public static User.PersonalData givenUserPersonalData() {
        return User.PersonalData.of("firstName", "lastName");
    }

    public static Password givenPassword() {
        return Password.of("password");
    }

    public static Role givenRole(int seed) {
        return new Role(
                givenRoleId(seed),
                givenRoleRestaurantId(seed),
                givenRoleName(seed),
                Collections.singleton(Permission.OWNER)
        );
    }

    public static Role.Id givenRoleId(int seed) {
        return Role.Id.of(UUID.nameUUIDFromBytes(("role-%s".formatted(seed)).getBytes()));
    }

    public static Role.RestaurantId givenRoleRestaurantId(int seed) {
        return Role.RestaurantId.of(UUID.nameUUIDFromBytes("restaurant-%s".formatted(seed).getBytes()));
    }

    public static TranslatedString givenRoleName(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("role-%s".formatted(seed))));
    }
}
