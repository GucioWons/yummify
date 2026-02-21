package com.guciowons.yummify.auth.domain.model;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private ExternalId id;
    private final RestaurantId restaurantId;
    private final Email email;
    private final Username username;
    private final PersonalData personalData;
    private final Password password;

    public static User create(
            RestaurantId restaurantId,
            Email email,
            Username username,
            PersonalData personalData,
            Password password
    ) {
        return new User(restaurantId, email, username, personalData, password);
    }

    public void assignId(ExternalId id) {
        if (this.id != null) {
            throw new IllegalStateException("Id already assigned");
        }

        this.id = id;
    }

    public boolean hasPassword() {
        return password != null;
    }

    public record ExternalId(UUID value) implements IdValueObject {
        public static ExternalId of(String value) {
            return new ExternalId(UUID.fromString(value));
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }

    public record Email(String value) implements ValueObject<String> {
        public static Email of(String value) {
            return new Email(value);
        }
    }

    public record Username(String value) implements ValueObject<String> {
        public static Username of(String value) {
            return new Username(value);
        }
    }

    public record PersonalData(String firstName, String lastName) {
        public static PersonalData of(String firstName, String lastName) {
            return new PersonalData(firstName, lastName);
        }
    }
}
