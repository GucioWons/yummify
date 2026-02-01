package com.guciowons.yummify.common.exception.domain.model;

import java.util.Map;

public record DomainError(
        ErrorMessage errorMessage,
        Map<String, Object> properties
) {
    public DomainError(ErrorMessage errorMessage) {
        this(errorMessage, null);
    }
}
