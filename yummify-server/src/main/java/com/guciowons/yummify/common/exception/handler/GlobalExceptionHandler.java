package com.guciowons.yummify.common.exception.handler;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.dto.ApiErrorResponseDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleUnexpectedException(Exception e, WebRequest request) {
        ApiErrorResponseDTO response = buildUnexpectedApiErrorResponseDTO(request);
        return handleExceptionInternal(e, response, new HttpHeaders(), response.getHttpStatus(), request);
    }

    @ExceptionHandler(value = { SingleApiErrorException.class })
    protected ResponseEntity<Object> handleUnexpectedException(SingleApiErrorException e, WebRequest request) {
        ApiErrorResponseDTO response = buildSingleApiErrorResponseDTO(e.getApiError(), request);
        return handleExceptionInternal(e, response, new HttpHeaders(), response.getHttpStatus(), request);
    }

    private ApiErrorResponseDTO buildUnexpectedApiErrorResponseDTO(WebRequest request) {
        ApiErrorDTO apiError = ApiErrorDTO.builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR).build();
        return buildSingleApiErrorResponseDTO(apiError, request);
    }

    private ApiErrorResponseDTO buildSingleApiErrorResponseDTO(ApiErrorDTO apiError, WebRequest request) {
        return new ApiErrorResponseDTO(apiError, request.getContextPath());
    }
}
