package com.guciowons.yummify.order.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.order.domain.exception.OrderDomainException;
import com.guciowons.yummify.order.domain.exception.OrderTableNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderTableId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class OrderExceptionStatusResolverTest {
    private final OrderExceptionStatusResolver underTest = new OrderExceptionStatusResolver();

    @Test
    void shouldSupportOrderDomainException() {
        // given
        var exception = mock(OrderDomainException.class);

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
                Arguments.of(new OrderTableNotFoundException(givenOrderTableId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}