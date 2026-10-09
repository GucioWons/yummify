package com.guciowons.yummify.common.file.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.common.file.domain.exception.FileDomainException;
import com.guciowons.yummify.common.file.domain.exception.InvalidStorageKeyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FileExceptionStatusResolverTest {
    private final FileExceptionStatusResolver underTest = new FileExceptionStatusResolver();

    @Test
    void shouldSupportFileDomainException() {
        // given
        var exception = mock(FileDomainException.class);

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
                Arguments.of(InvalidStorageKeyException.blank(), HttpStatus.BAD_REQUEST),
                Arguments.of(new CannotGetFileException(), HttpStatus.INTERNAL_SERVER_ERROR),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}