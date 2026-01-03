package com.guciowons.yummify.common.exception.application.handler;

import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.common.exception.application.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.application.dto.ApiErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class UnexpectedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleUnexpectedException(Exception e, WebRequest request) {
        log.error(e.getMessage(), e);
        ApiErrorResponseDTO response = buildUnexpectedApiErrorResponseDTO(request);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ApiErrorResponseDTO buildUnexpectedApiErrorResponseDTO(WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(CommonErrorMessage.UNEXPECTED_SERVER_ERROR);
        return new ApiErrorResponseDTO(request.getDescription(false), apiError);
    }
}
