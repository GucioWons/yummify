package com.guciowons.yummify.common.exception.handler;

import com.guciowons.yummify.common.exception.MultipleApiErrorException;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.dto.ApiErrorResponseDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

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

    @ExceptionHandler(value = { MultipleApiErrorException.class })
    protected ResponseEntity<Object> handleUnexpectedException(MultipleApiErrorException e, WebRequest request) {
        ApiErrorResponseDTO response = buildMultipleApiErrorResponseDTO(e.getApiErrors(), request);
        return handleExceptionInternal(e, response, new HttpHeaders(), response.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, @NonNull HttpHeaders headers, @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {
        List<ApiErrorDTO> apiErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    ErrorMessage message = ErrorMessage.VALIDATION_NULL;

                    return ApiErrorDTO.builder(message, HttpStatus.BAD_REQUEST)
                            .errorLocationType(ErrorLocationType.BODY)
                            .location(fieldError.getField())
                            .build();
                })
                .toList();
        ApiErrorResponseDTO response = buildMultipleApiErrorResponseDTO(apiErrors, request);
        return handleExceptionInternal(e, response, new HttpHeaders(), response.getHttpStatus(), request);
    }

    private ApiErrorResponseDTO buildUnexpectedApiErrorResponseDTO(WebRequest request) {
        ApiErrorDTO apiError = ApiErrorDTO
                .builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return buildSingleApiErrorResponseDTO(apiError, request);
    }

    private ApiErrorResponseDTO buildSingleApiErrorResponseDTO(ApiErrorDTO apiError, WebRequest request) {
        return new ApiErrorResponseDTO(apiError, request.getDescription(false));
    }

    private ApiErrorResponseDTO buildMultipleApiErrorResponseDTO(List<ApiErrorDTO> apiErrors, WebRequest request) {
        return new ApiErrorResponseDTO(apiErrors, request.getDescription(false));
    }
}
