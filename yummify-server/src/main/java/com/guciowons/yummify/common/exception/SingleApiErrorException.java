package com.guciowons.yummify.common.exception;

import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import lombok.Getter;

@Getter
public class SingleApiErrorException extends RuntimeException {
    private final ApiErrorDTO apiError;

    public SingleApiErrorException(ApiErrorDTO.Builder apiError) {
        this.apiError = apiError.build();
    }
}
