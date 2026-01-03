package com.guciowons.yummify.common.exception.application.dto;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;

import java.util.Map;

public record ApiErrorDTO(String errorMessageString, ErrorMessage errorMessage, Map<String, Object> properties) {
    public ApiErrorDTO(ErrorMessage errorMessage) {
        this(errorMessage.getMessage(), errorMessage, null);
    }
}
