package com.guciowons.yummify.file.domain.model;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record Filename(String value) implements ValueObject<String> {
    public static Filename of(String value) {
        return new Filename(value);
    }
}
