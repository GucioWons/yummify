package com.guciowons.yummify.auth.application.exception;

import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class AuthExceptionMapperTest {
    private final AuthExceptionMapper underTest = new AuthExceptionMapper();

    @Test
    void shouldMapAccountExistsByEmailExceptionToApiException() {
        // given
        var exception = new AccountExistsByEmailException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_EMAIL);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getProperties()).isNull();
    }

    @Test
    void shouldMapAccountExistsByUsernameExceptionToApiException() {
        // given
        var exception = new AccountExistsByUsernameException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(AuthErrorMessage.AUTH_ACCOUNT_EXISTS_BY_USERNAME);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getProperties()).isNull();
    }

    @Test
    void shouldMapNotImplementedExceptionToApiException() {
        // given
        var exception = new DomainException("Exception message");

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(CommonErrorMessage.DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(result.getProperties()).isNull();
    }
}