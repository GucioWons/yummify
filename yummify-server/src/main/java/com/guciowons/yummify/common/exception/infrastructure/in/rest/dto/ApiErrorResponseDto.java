package com.guciowons.yummify.common.exception.infrastructure.in.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponseDto(String path, LocalDateTime errorOccurredAt, List<ApiErrorDto> apiErrors) {
    public ApiErrorResponseDto(String path, ApiErrorDto apiError) {
        this(path, List.of(apiError));
    }

    public ApiErrorResponseDto(String path, List<ApiErrorDto> apiErrors) {
        this(path, LocalDateTime.now(), apiErrors);
    }
}
