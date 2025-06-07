package com.guciowons.yummify.common.security.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends SingleApiErrorException {
    public UnauthorizedException() {
        super(ApiErrorDTO.builder(ErrorMessage.UNAUTHORIZED, HttpStatus.UNAUTHORIZED)
                .errorLocationType(ErrorLocationType.AUTH)
                .location("jwt")
        );
    }
}
