package com.guciowons.yummify.common.exception.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorMessage implements ErrorMessage {
    VALIDATION_NOT_NULL("This field is required"),
    VALIDATION_NULL("This field has to be null"),
    UNEXPECTED_SERVER_ERROR("Unexpected server error");

    private final String message;
}
