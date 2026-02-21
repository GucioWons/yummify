package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorResponseDTO;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ApiException.class })
    protected ResponseEntity<Object> handleUnexpectedException(ApiException e, WebRequest request) {
        log.error(e.getMessage(), e);
        ApiErrorResponseDTO response = buildUnexpectedApiErrorResponseDTO(e, request);
        return handleExceptionInternal(e, response, new HttpHeaders(), e.getHttpStatus(), request);
    }

    private ApiErrorResponseDTO buildUnexpectedApiErrorResponseDTO(ApiException exception, WebRequest request) {
        String filledErrorMessage = fillErrorMessage(exception.getErrorMessage(), exception.getProperties());
        ApiErrorDTO apiError = new ApiErrorDTO(filledErrorMessage, exception.getErrorMessage(), exception.getProperties());
        return new ApiErrorResponseDTO(request.getDescription(false), apiError);
    }

    private String fillErrorMessage(ErrorMessage errorMessage, Map<String, Object> properties) {
        String message = errorMessage.getMessage();

        if (properties == null || properties.isEmpty()) {
            return message;
        }

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            message = fillProperty(message, entry.getKey(), entry.getValue());
        }
        return message;
    }

    private String fillProperty(String message, String key, Object value) {
        String placeholder = "{{%s}}".formatted(key);
        return message.replace(placeholder, value.toString());
    }
}
