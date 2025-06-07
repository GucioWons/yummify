package com.guciowons.yummify.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ApiErrorDTO {
    private final String errorMessageString;
    private final ErrorLocationType errorLocationType;
    private final ErrorMessage errorMessage;
    private final String location;
    @JsonIgnore
    private final HttpStatus httpStatus;

    private ApiErrorDTO(Builder builder) {
        this.errorMessageString = builder.errorMessageString;
        this.errorLocationType = builder.errorLocationType;
        this.errorMessage = builder.errorMessage;
        this.location = builder.location;
        this.httpStatus = builder.httpStatus;
    }

    public static ApiErrorDTO.Builder builder(ErrorMessage errorMessage, HttpStatus httpStatus){
        return new ApiErrorDTO.Builder(errorMessage, httpStatus);
    }

    public static class Builder {
        private final ErrorMessage errorMessage;
        private final HttpStatus httpStatus;

        private String errorMessageString;
        private ErrorLocationType errorLocationType;
        private String location;
        private final Map<String, Object> textParams = new HashMap<>();

        public Builder(ErrorMessage errorMessage, HttpStatus httpStatus) {
            this.errorMessage = errorMessage;
            this.httpStatus = httpStatus;
        }

        public Builder errorLocationType(ErrorLocationType errorLocationType) {
            this.errorLocationType = errorLocationType;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder textParam(String key, Object value) {
            this.textParams.put(key, value);
            return this;
        }

        public ApiErrorDTO build() {
            errorMessageString = fillPlaceholders(errorMessage.getMessage(), textParams);
            return new ApiErrorDTO(this);
        }

        private String fillPlaceholders(String message, Map<String, Object> placeholders) {
            if (placeholders == null) {
                return message;
            }
            String result = message;

            for (Map.Entry<String, Object> entry : placeholders.entrySet()) {
                String placeholder = "{{" + entry.getKey() + "}}";
                result = result.replace(placeholder, String.valueOf(entry.getValue()));
            }

            return result;
        }
    }
}
