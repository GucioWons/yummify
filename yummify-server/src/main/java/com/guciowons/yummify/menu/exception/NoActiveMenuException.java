package com.guciowons.yummify.menu.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class NoActiveMenuException extends SingleApiErrorException {
    public NoActiveMenuException() {
        super(ApiErrorDTO.builder(ErrorMessage.NO_ACTIVE_MENU, HttpStatus.NOT_FOUND)
                .errorLocationType(ErrorLocationType.HEADER_PARAM)
        );
    }
}
