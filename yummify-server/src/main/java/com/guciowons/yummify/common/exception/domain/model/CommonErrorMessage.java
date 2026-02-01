package com.guciowons.yummify.common.exception.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorMessage implements ErrorMessage {
    VALIDATION_NOT_NULL("This field is required"),
    VALIDATION_NULL("This field has to be null"),
    DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED("Exception handling is not implemented"),
    UNEXPECTED_SERVER_ERROR("Unexpected server error");

    private final String message;
}
