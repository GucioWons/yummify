package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class CannotGetFileException extends SingleApiErrorException {
    public CannotGetFileException() {
        super(ApiErrorDTO.builder(ErrorMessage.CANNOT_GET_FILE, HttpStatus.BAD_REQUEST)
                .errorLocationType(ErrorLocationType.FILE)
        );
    }
}
