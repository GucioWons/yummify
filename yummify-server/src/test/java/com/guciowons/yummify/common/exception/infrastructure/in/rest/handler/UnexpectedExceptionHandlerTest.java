package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import static com.guciowons.yummify.common.exception.helper.ApiErrorResponseAssert.assertApiError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnexpectedExceptionHandlerTest {
    private final UnexpectedExceptionHandler underTest = new UnexpectedExceptionHandler();

    @Test
    void shouldHandleUnexpectedException() {
        // given
        var exception = new RuntimeException("Unexpected error");
        var request = mock(WebRequest.class);

        when(request.getDescription(false)).thenReturn("uri=/test");

        // when
        var response = underTest.handleUnexpectedException(exception, request);

        // then
        assertApiError(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasMessage(CommonErrorMessage.UNEXPECTED_SERVER_ERROR);
    }
}