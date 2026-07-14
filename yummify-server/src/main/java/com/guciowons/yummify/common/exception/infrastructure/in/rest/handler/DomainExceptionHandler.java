package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorDto;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class DomainExceptionHandler extends ResponseEntityExceptionHandler {
    private final List<ExceptionStatusResolver> exceptionStatusResolvers;

    @ExceptionHandler(value = DomainException.class)
    protected ResponseEntity<Object> handleUnexpectedException(DomainException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        ApiErrorResponseDto response = buildApiErrorResponseDto(exception, request);
        HttpStatus status = getHttpStatus(exception);
        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }

    private ApiErrorResponseDto buildApiErrorResponseDto(DomainException exception, WebRequest request) {
        ApiErrorDto apiError = new ApiErrorDto(exception.getMessage(), exception.getErrorMessage(), exception.getPropertiesAsMap());
        return new ApiErrorResponseDto(request.getDescription(false), apiError);
    }

    private HttpStatus getHttpStatus(DomainException exception) {
        return exceptionStatusResolvers.stream()
                .filter(resolver -> resolver.supports(exception))
                .findFirst()
                .map(resolver -> resolver.resolve(exception))
                .orElseThrow();
    }
}
