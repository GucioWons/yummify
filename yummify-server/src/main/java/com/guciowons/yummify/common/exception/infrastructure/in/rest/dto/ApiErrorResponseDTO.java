package com.guciowons.yummify.common.exception.infrastructure.in.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponseDTO(String path, LocalDateTime errorOccurredAt, List<ApiErrorDTO> apiErrors) {
    public ApiErrorResponseDTO(String path, ApiErrorDTO apiError) {
        this(path, List.of(apiError));
    }

    public ApiErrorResponseDTO(String path, List<ApiErrorDTO> apiErrors) {
        this(path, LocalDateTime.now(), apiErrors);
    }
}
