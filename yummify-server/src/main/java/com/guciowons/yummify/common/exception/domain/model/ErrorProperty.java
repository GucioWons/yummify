package com.guciowons.yummify.common.exception.domain.model;

public record ErrorProperty(String key, Object value) {
    public static ErrorProperty of(String key, Object value) {
        return new ErrorProperty(key, value);
    }
}
