package com.guciowons.yummify.common.security.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends SingleApiErrorException {
    public AccessDeniedException() {
        super(ApiErrorDTO.builder(ErrorMessage.ACCESS_DENIED, HttpStatus.FORBIDDEN));
    }
}
