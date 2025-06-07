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

    public ApiErrorResponseDTO(List<ApiErrorDTO.Builder> apiErrorBuilderList, String path) {
        this.apiErrorList = apiErrorBuilderList.stream().map(ApiErrorDTO.Builder::build).toList();
        this.path = path;
        this.httpStatus = apiErrorList.getFirst().getHttpStatus();
    }
}
