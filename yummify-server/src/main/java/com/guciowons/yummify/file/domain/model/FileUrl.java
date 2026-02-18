package com.guciowons.yummify.file.domain.model;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

import java.net.URL;

public record FileUrl(URL value) implements ValueObject<URL> {
    public static FileUrl of(URL value) {
        return new FileUrl(value);
    }
}
