package com.guciowons.yummify.common.i8n.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record Translation(String value) implements ValueObject<String> {
    public static Translation of(String value) {
        return new Translation(value);
    }
}
