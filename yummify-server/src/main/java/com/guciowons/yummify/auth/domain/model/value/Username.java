package com.guciowons.yummify.auth.domain.model.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record Username(String value) implements ValueObject<String> {
    public static Username of(String value) {
        return new Username(value);
    }
}
