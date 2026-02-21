package com.guciowons.yummify.auth.domain.model;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record Password(String value) implements ValueObject<String> {
    public static Password of(String value) {
        return new Password(value);
    }
}
