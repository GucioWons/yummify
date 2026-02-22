package com.guciowons.yummify.menu.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.menu.domain.exception.*;
import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.guciowons.yummify.menu.domain.fixture.MenuDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class MenuDomainExceptionMapperTest {
    private final MenuDomainExceptionMapper underTest = new MenuDomainExceptionMapper();

    @Test
    void shouldMapDraftMenuVersionNotFoundExceptionToApiException() {
        // given
        var exception = new DraftMenuVersionNotFoundException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.DRAFT_MENU_VERSION_NOT_FOUND);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).isEmpty();
    }

    @Test
    void shouldMapPublishedMenuVersionNotFoundExceptionToApiException() {
        // given
        var exception = new PublishedMenuVersionNotFoundException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.PUBLISHED_MENU_VERSION_NOT_FOUND);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).isEmpty();
    }

    @Test
    void shouldMapArchivedMenuVersionNotFoundExceptionToApiException() {
        // given
        var id = givenMenuVersionId(1);
        var exception = new ArchivedMenuNotFoundException(id);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.ARCHIVED_MENU_VERSION_NOT_FOUND);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", id.value().toString());
    }

    @Test
    void shouldMapMenuSectionNotFoundExceptionToApiException() {
        // given
        var sectionId = givenMenuSectionId(1);
        var exception = new MenuSectionNotFoundException(sectionId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.MENU_SECTION_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", sectionId.value().toString());
    }

    @Test
    void shouldMapMenuEntryNotFoundExceptionToApiException() {
        // given
        var entryId = givenMenuEntryId(1);
        var exception = new MenuEntryNotFoundException(entryId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.MENU_ENTRY_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", entryId.value().toString());
    }

    @Test
    void shouldMapCannotUpdateMenuSectionPositionExceptionToApiException() {
        // given
        var exception = new CannotUpdateMenuSectionPositionException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.CANNOT_UPDATE_MENU_SECTION_POSITION);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getProperties()).isNull();
    }

    @Test
    void shouldMapMenuVersionIsNotDraftExceptionToApiException() {
        // given
        var exception = new MenuVersionIsNotDraftException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.MENU_VERSION_IS_NOT_DRAFT);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getProperties()).isEmpty();
    }

    @Test
    void shouldMapMMenuVersionAlreadyExistsExceptionToApiException() {
        // given
        var exception = new MenuVersionAlreadyExistsException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.MENU_VERSION_ALREADY_EXISTS);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getProperties()).isEmpty();
    }

    @Test
    void shouldMapMenuVersionIsNotPublishedExceptionToApiException() {
        // given
        var exception = new MenuVersionIsNotPublishedException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(MenuErrorMessage.MENU_VERSION_IS_NOT_PUBLISHED);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getProperties()).isEmpty();
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