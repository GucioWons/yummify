package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Map<String, CommonErrorMessage> errorMessageMap = Map.of(
            "Null", CommonErrorMessage.VALIDATION_NULL,
            "NotNull", CommonErrorMessage.VALIDATION_NOT_NULL
    );

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, @NonNull HttpHeaders headers, @NonNull HttpStatusCode statusCode, @NonNull WebRequest request) {
        ApiErrorResponseDTO response = buildApiErrorResponse(e.getBindingResult().getFieldErrors(), request);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    private ApiErrorResponseDTO buildApiErrorResponse(List<FieldError> fieldErrors, WebRequest request) {
        List<ApiErrorDTO> apiErrors = fieldErrors.stream()
                .map(this::buildFieldApiError)
                .toList();

        return new ApiErrorResponseDTO(request.getDescription(false), apiErrors);
    }

    private ApiErrorDTO buildFieldApiError(FieldError apiError) {
        CommonErrorMessage errorMessage = errorMessageMap.getOrDefault(
                getValidationName(apiError.getCodes()),
                CommonErrorMessage.UNEXPECTED_SERVER_ERROR
        );

        return new ApiErrorDTO(errorMessage);
    }

    private String getValidationName(String[] codes) {
        if (codes == null || codes.length < 4) {
            throw new IllegalArgumentException();
        }
        return codes[3];
    }
}
