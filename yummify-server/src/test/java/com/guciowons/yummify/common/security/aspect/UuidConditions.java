package com.guciowons.yummify.common.security.aspect;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Condition;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UuidConditions {
    public static Condition<String> validUuid() {
        return new Condition<>(
                value -> {
                    try {
                        UUID.fromString(value);
                        return true;
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                },
                "valid UUID"
        );
    }
}
