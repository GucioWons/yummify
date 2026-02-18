package com.guciowons.yummify.file.domain.model;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record Directory(String value) implements ValueObject<String> {
    public static Directory of(String value) {
        return new Directory(value);
    }
}
