package com.guciowons.yummify.menu.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.menu.domain.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MenuExceptionStatusResolverTest {
    private final MenuExceptionStatusResolver underTest = new MenuExceptionStatusResolver();

    @Test
    void shouldSupportMenuDomainException() {
        // given
        var exception = mock(MenuDomainException.class);

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
                Arguments.of(new DraftMenuVersionNotFoundException(), HttpStatus.NOT_FOUND),
                Arguments.of(new PublishedMenuVersionNotFoundException(), HttpStatus.NOT_FOUND),
                Arguments.of(new ArchivedMenuNotFoundException(givenMenuVersionId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(new MenuSectionNotFoundException(givenMenuSectionId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(new MenuEntryNotFoundException(givenMenuEntryId(1)), HttpStatus.NOT_FOUND),
                Arguments.of(new CannotUpdateMenuSectionPositionException(), HttpStatus.BAD_REQUEST),
                Arguments.of(new MenuVersionIsNotDraftException(), HttpStatus.CONFLICT),
                Arguments.of(new MenuVersionAlreadyExistsException(), HttpStatus.CONFLICT),
                Arguments.of(new MenuVersionIsNotPublishedException(), HttpStatus.CONFLICT),
                Arguments.of(mock(DomainException.class), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}