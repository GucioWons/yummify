package com.guciowons.yummify.common.exception.handler;

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

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleUnexpectedException(Exception e, WebRequest request) {
        ApiErrorResponseDTO response = buildResponseDTO(request);
        return handleExceptionInternal(e, buildResponseDTO(request), new HttpHeaders(), response.getHttpStatus(), request);
    }

    private ApiErrorResponseDTO buildResponseDTO(WebRequest request) {
        return new ApiErrorResponseDTO(
                List.of(ApiErrorDTO.builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)),
                request.getContextPath()
        );
    }
}
