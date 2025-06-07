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
    private final List<ApiErrorDTO> apiErrorList;

    public ApiErrorResponseDTO(List<ApiErrorDTO> apiErrorList, String path) {
        this.apiErrorList = apiErrorList;
        this.path = path;
        this.httpStatus = apiErrorList.getFirst().getHttpStatus();
    }

    public ApiErrorResponseDTO(ApiErrorDTO apiError, String path) {
        this.apiErrorList = List.of(apiError);
        this.path = path;
        this.httpStatus = apiError.getHttpStatus();
    }
}
