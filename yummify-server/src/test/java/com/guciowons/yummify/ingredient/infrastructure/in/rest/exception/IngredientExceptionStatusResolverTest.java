package com.guciowons.yummify.ingredient.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.ingredient.domain.exception.IngredientDomainException;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class IngredientExceptionStatusResolverTest {
    private final IngredientExceptionStatusResolver underTest = new IngredientExceptionStatusResolver();

    @Test
    void shouldSupportIngredientDomainException() {
        // given
        var exception = mock(IngredientDomainException.class);

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
                Arguments.of(new IngredientNotFoundException(givenIngredientId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}
