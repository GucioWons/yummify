package com.guciowons.yummify.common.exception.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;

import java.util.Map;

public record ApiErrorDto(String errorMessageString, ErrorMessage errorMessage, Map<String, Object> properties) {
    public ApiErrorDto(ErrorMessage errorMessage) {
        this(errorMessage.getMessage(), errorMessage, null);
    }
}
