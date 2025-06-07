package com.guciowons.yummify.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApiErrorResponseDTO {
    @JsonIgnore
    private final HttpStatus httpStatus;
    private final LocalDateTime errorOccurredAt = LocalDateTime.now();
    private final List<ApiErrorDTO> apiErrorList;
}
