package com.guciowons.yummify.common.exception.application.handler;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.application.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.application.dto.ApiErrorMapper;
import com.guciowons.yummify.common.exception.application.dto.ApiErrorResponseDTO;
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
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {
    private final ApiErrorMapper apiErrorMapper;

    @ExceptionHandler(value = { DomainException.class })
    protected ResponseEntity<Object> handleUnexpectedException(DomainException e, WebRequest request) {
        ApiErrorDTO apiError = apiErrorMapper.mapToDTO(e.getDomainError());
        ApiErrorResponseDTO response = new ApiErrorResponseDTO(request.getDescription(false), apiError);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
