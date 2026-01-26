package com.guciowons.yummify.file.application.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.guciowons.yummify.file.domain.fixture.FileDomainFixture.givenFileId;
import static org.assertj.core.api.Assertions.assertThat;

class FileDomainExceptionMapperTest {
    private final FileDomainExceptionMapper underTest = new FileDomainExceptionMapper();

    @Test
    void shouldMapFileNotFoundExceptionToApiException() {
        // given
        var fileId = givenFileId(1);
        var exception = new FileNotFoundException(fileId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(FileErrorMessage.FILE_NOT_FOUND_EXCEPTION);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", fileId.value().toString());
    }

    @Test
    void shouldMapCannotGetFileExceptionToApiException() {
        // given
        var exception = new CannotGetFileException();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(FileErrorMessage.CANNOT_GET_FILE);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getProperties()).isNull();
    }

    @Test
    void shouldMapInvalidStorageKeyExceptionWithBlankReasonToApiException() {
        // given
        var exception = InvalidStorageKeyException.blank();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(FileErrorMessage.STORAGE_KEY_IS_BLANK);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getProperties()).isNull();
    }

    @Test
    void shouldMapInvalidStorageKeyExceptionWithStartsWithSlashReasonToApiException() {
        // given
        var exception = InvalidStorageKeyException.startsWithSlash();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(FileErrorMessage.STORAGE_KEY_STARTS_WITH_SLASH);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(result.getProperties()).isNull();
    }

    @Test
    void shouldMapInvalidStorageKeyExceptionWithContainsDotsReasonToApiException() {
        // given
        var exception = InvalidStorageKeyException.containsDots();

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(FileErrorMessage.STORAGE_KEY_CONTAINS_DOTS);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
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