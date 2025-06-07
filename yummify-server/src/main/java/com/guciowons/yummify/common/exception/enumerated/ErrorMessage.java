package com.guciowons.yummify.common.exception.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    UNEXPECTED_SERVER_ERROR("Unexpected server error");

    private final String message;
}
