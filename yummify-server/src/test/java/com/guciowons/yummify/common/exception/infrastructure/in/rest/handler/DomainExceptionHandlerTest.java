package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.NoSuchElementException;

import static com.guciowons.yummify.common.exception.helper.ApiErrorResponseAssert.assertApiError;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DomainExceptionHandlerTest {
    private final ExceptionStatusResolver exceptionStatusResolver = mock(ExceptionStatusResolver.class);

    private final DomainExceptionHandler underTest = new DomainExceptionHandler(List.of(exceptionStatusResolver));

    @Test
    void shouldHandleDomainException() {
        // given
        var exception = new TestDomainException();
        var request = mock(WebRequest.class);

        when(exceptionStatusResolver.supports(exception)).thenReturn(true);
        when(exceptionStatusResolver.resolve(exception)).thenReturn(HttpStatus.BAD_REQUEST);
        when(request.getDescription(false)).thenReturn("uri=/test");

        // when
        var response = underTest.handleUnexpectedException(exception, request);

        // then
        assertApiError(response)
                .hasStatus(HttpStatus.BAD_REQUEST)
                .hasMessage(CommonErrorMessage.DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED)
                .hasProperty("test", "test");
    }


    @Test
    void shouldThrowExceptionWhenNoResolverFound() {
        // given
        var exception = new TestDomainException();

        when(exceptionStatusResolver.supports(exception)).thenReturn(false);

        // when & then
        assertThatThrownBy(() -> underTest.handleUnexpectedException(exception, mock(WebRequest.class)))
                .isInstanceOf(NoSuchElementException.class);
    }
}