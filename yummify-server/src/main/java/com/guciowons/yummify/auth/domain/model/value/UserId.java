package com.guciowons.yummify.auth.domain.model.value;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record UserId(UUID value) implements IdValueObject {
    public static UserId of(UUID value) {
        return new UserId(value);
    }

    public static UserId of(String value) {
        return new UserId(UUID.fromString(value));
    }
}
