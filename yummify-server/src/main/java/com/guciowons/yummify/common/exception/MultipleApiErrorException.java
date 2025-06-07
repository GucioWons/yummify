package com.guciowons.yummify.common.exception;

import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class MultipleApiErrorException extends RuntimeException {
    private final List<ApiErrorDTO> apiErrors;
    public MultipleApiErrorException(List<ApiErrorDTO.Builder> apiErrorBuilders) {
      this.apiErrors = apiErrorBuilders.stream().map(ApiErrorDTO.Builder::build).toList();
    }
}
