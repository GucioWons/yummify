package com.guciowons.yummify.file.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;

import java.util.Map;

@ExceptionMapper
public class FileDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case FileNotFoundException ex -> mapFileNotFoundException(ex);
            case CannotGetFileException ex -> mapCannotGetFileException(ex);
            case InvalidStorageKeyException ex -> mapInvalidStorageKeyException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapFileNotFoundException(FileNotFoundException exception) {
        return ApiException.notFound(
                exception,
                FileErrorMessage.FILE_NOT_FOUND_EXCEPTION,
                Map.of("id", exception.getId().value().toString())
        );
    }

    private ApiException mapCannotGetFileException(CannotGetFileException ex) {
        return ApiException.internalServerError(ex, FileErrorMessage.CANNOT_GET_FILE);
    }

    private ApiException mapInvalidStorageKeyException(InvalidStorageKeyException ex) {
        FileErrorMessage message = switch (ex.getReason()) {
            case BLANK -> FileErrorMessage.STORAGE_KEY_IS_BLANK;
            case STARTS_WITH_SLASH -> FileErrorMessage.STORAGE_KEY_STARTS_WITH_SLASH;
            case CONTAINS_DOTS -> FileErrorMessage.STORAGE_KEY_CONTAINS_DOTS;
        };

        return ApiException.badRequest(ex, message);
    }
}
