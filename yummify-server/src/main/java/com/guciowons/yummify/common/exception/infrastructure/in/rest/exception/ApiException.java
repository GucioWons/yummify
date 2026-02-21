package com.guciowons.yummify.common.exception.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ErrorMessage errorMessage;
    private final Map<String, Object> properties;

    public static ApiException notImplemented(Throwable cause) {
        return new ApiException(
                cause,
                HttpStatus.SERVICE_UNAVAILABLE,
                CommonErrorMessage.DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED,
                null
        );
    }

    public static ApiException notFound(Throwable cause, ErrorMessage message, Map<String,Object> props) {
        return new ApiException(cause, HttpStatus.NOT_FOUND, message, props);
    }

    public static ApiException conflict(Throwable cause, ErrorMessage message, Map<String,Object> props) {
        return new ApiException(cause, HttpStatus.CONFLICT, message, props);
    }

    public static ApiException badRequest(Throwable cause, ErrorMessage message) {
        return new ApiException(cause, HttpStatus.BAD_REQUEST, message, null);
    }

    public static ApiException internalServerError(Throwable cause, ErrorMessage message) {
        return new ApiException(cause, HttpStatus.INTERNAL_SERVER_ERROR, message, null);
    }

    private ApiException(Throwable cause, HttpStatus httpStatus, ErrorMessage errorMessage, Map<String, Object> properties) {
        super(buildErrorMessage(errorMessage, properties), cause);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.properties = properties;
    }

    private static String buildErrorMessage(ErrorMessage errorMessage, Map<String, Object> properties) {
        if (properties == null || properties.isEmpty()) {
            return errorMessage.getMessage();
        }

        String result = errorMessage.getMessage();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String placeholder = "{{" + entry.getKey() + "}}";
            result = result.replace(placeholder, String.valueOf(entry.getValue()));
        }
        return result;
    }
}
