package com.guciowons.yummify.common.exception.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    RESTAURANT_NOT_FOUND_BY_ID("Could not find restaurant with ID {{id}}"),

    UNEXPECTED_SERVER_ERROR("Unexpected server error");

    private final String message;
}
