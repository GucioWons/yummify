package com.guciowons.yummify.common.exception.helper;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorDto;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.dto.ApiErrorResponseDto;
import org.assertj.core.api.AbstractAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiErrorResponseAssert extends AbstractAssert<ApiErrorResponseAssert, ResponseEntity<Object>> {
    private ApiErrorResponseDto responseDto;

    private ApiErrorResponseAssert(ResponseEntity<Object> actual) {
        super(actual, ApiErrorResponseAssert.class);

        if (actual != null && actual.getBody() instanceof ApiErrorResponseDto dto) {
            this.responseDto = dto;
        }
    }

    public static ApiErrorResponseAssert assertApiError(ResponseEntity<Object> actual) {
        return new ApiErrorResponseAssert(actual);
    }

    public ApiErrorResponseAssert hasStatus(HttpStatus expectedStatus) {
        isNotNull();

        if (!actual.getStatusCode().equals(expectedStatus)) {
            failWithMessage("Expected status <%s> but was <%s>", expectedStatus, actual.getStatusCode());
        }

        return this;
    }

    public ApiErrorResponseAssert hasMessage(ErrorMessage expectedMessage) {
        isNotNull();
        hasApiErrorResponse();

        ApiErrorDto error = responseDto.apiErrors().getFirst();

        if (!error.errorMessage().equals(expectedMessage)) {
            failWithMessage("Expected error message <%s> but was <%s>", expectedMessage, error.errorMessage());
        }

        return this;
    }

    private void hasApiErrorResponse() {
        if (responseDto == null) {
            failWithMessage("Expected body to be ApiErrorResponseDto but was <%s>", actual.getBody());
        }
    }

    public ApiErrorResponseAssert hasProperty(String key, Object value) {
        isNotNull();

        ApiErrorDto error = responseDto.apiErrors().getFirst();

        assertThat(error.properties()).containsEntry(key, value);

        return this;
    }
}
