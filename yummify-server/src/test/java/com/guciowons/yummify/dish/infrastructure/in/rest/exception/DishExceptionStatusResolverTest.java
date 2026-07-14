package com.guciowons.yummify.dish.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.dish.domain.exception.DishDomainException;
import com.guciowons.yummify.dish.domain.exception.DishIngredientsNotFoundException;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishId;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishIngredientIds;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DishExceptionStatusResolverTest {
    private final DishExceptionStatusResolver underTest = new DishExceptionStatusResolver();

    @Test
    void shouldSupportDishDomainException() {
        // given
        var exception = mock(DishDomainException.class);

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
                Arguments.of(new DishNotFoundException(givenDishId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(new DishIngredientsNotFoundException(givenDishIngredientIds(1)), HttpStatus.NOT_FOUND),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}