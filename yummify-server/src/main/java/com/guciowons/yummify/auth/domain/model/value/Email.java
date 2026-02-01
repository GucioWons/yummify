package com.guciowons.yummify.auth.domain.model.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record Email(String value) implements ValueObject<String> {
    public static Email of(String value) {
        return new Email(value);
    }
}
