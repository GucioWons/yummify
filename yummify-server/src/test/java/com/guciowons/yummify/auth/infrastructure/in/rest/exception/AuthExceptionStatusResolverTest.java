package com.guciowons.yummify.auth.infrastructure.in.rest.exception;

import com.guciowons.yummify.auth.domain.exception.AccountExistsByEmailException;
import com.guciowons.yummify.auth.domain.exception.AccountExistsByUsernameException;
import com.guciowons.yummify.auth.domain.exception.AuthDomainException;
import com.guciowons.yummify.auth.domain.exception.RoleNotFoundException;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRoleId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class AuthExceptionStatusResolverTest {
    private final AuthExceptionStatusResolver underTest = new AuthExceptionStatusResolver();

    @Test
    void shouldSupportAuthDomainException() {
        // given
        var exception = mock(AuthDomainException.class);

        // when
        var result = underTest.supports(exception);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldNotSupportOtherDomainException() {
        // given
        var exception = mock(DomainException.class);

        // when
        var result = underTest.supports(exception);

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideExceptionsWithExpectedStatus")
    void shouldResolveCorrectHttpStatus(DomainException exception, HttpStatus expectedStatus) {
        // when
        HttpStatus result = underTest.resolve(exception);

        // then
        assertThat(result).isEqualTo(expectedStatus);
    }

    private static Stream<Arguments> provideExceptionsWithExpectedStatus() {
        return Stream.of(
                Arguments.of(new AccountExistsByEmailException(), HttpStatus.CONFLICT),
                Arguments.of(new AccountExistsByUsernameException(), HttpStatus.CONFLICT),
                Arguments.of(new RoleNotFoundException(givenRoleId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}