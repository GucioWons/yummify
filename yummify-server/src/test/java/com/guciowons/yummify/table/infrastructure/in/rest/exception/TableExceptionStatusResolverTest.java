package com.guciowons.yummify.table.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.domain.exception.TableDomainException;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.exception.TableNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTableId;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTableName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class TableExceptionStatusResolverTest {
    private final TableExceptionStatusResolver underTest = new TableExceptionStatusResolver();

    @Test
    void shouldSupportTableDomainException() {
        // given
        var exception = mock(TableDomainException.class);

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
                Arguments.of(new TableExistsByNameException(givenTableName(1)), HttpStatus.CONFLICT),
                Arguments.of(new TableNotFoundException(givenTableId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}