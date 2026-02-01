package com.guciowons.yummify.common.security.application;

import com.guciowons.yummify.common.exception.application.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.application.dto.ApiErrorResponseDTO;
import com.guciowons.yummify.common.security.domain.AccessDeniedException;
import com.guciowons.yummify.common.security.domain.SecurityErrorMessage;
import com.guciowons.yummify.common.security.domain.UnauthorizedException;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { AccessDeniedException.class })
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        ApiErrorResponseDTO response = buildApiErrorResponse(SecurityErrorMessage.ACCESS_DENIED, request);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = { UnauthorizedException.class })
    protected ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException e, WebRequest request) {
        ApiErrorResponseDTO response = buildApiErrorResponse(SecurityErrorMessage.UNAUTHORIZED, request);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    private ApiErrorResponseDTO buildApiErrorResponse(SecurityErrorMessage message, WebRequest request) {
        ApiErrorDTO apiError = new ApiErrorDTO(message);
        return new ApiErrorResponseDTO(request.getDescription(false), apiError);
    }
}