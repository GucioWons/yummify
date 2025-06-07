package com.guciowons.yummify.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiErrorResponseDTO {
    @JsonIgnore
    private final HttpStatus httpStatus;
    private final String path;
    private final LocalDateTime errorOccurredAt = LocalDateTime.now();
    private final List<ApiErrorDTO> apiErrors;

    public ApiErrorResponseDTO(List<ApiErrorDTO> apiErrors, String path) {
        this.apiErrors = apiErrors;
        this.path = path;
        this.httpStatus = apiErrors.getFirst().getHttpStatus();
    }

    public ApiErrorResponseDTO(ApiErrorDTO apiError, String path) {
        this.apiErrors = List.of(apiError);
        this.path = path;
        this.httpStatus = apiError.getHttpStatus();
    }
}
