package com.guciowons.yummify.file.application.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.application.ApiException;
import com.guciowons.yummify.common.exception.application.mapper.DomainExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;

import java.util.Map;

@ExceptionMapper
public class FileDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case FileNotFoundException ex -> mapFileNotFoundException(ex);
            case CannotGetFileException ex -> mapCannotGetFileException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapFileNotFoundException(FileNotFoundException exception) {
        return ApiException.notFound(
                exception,
                FileErrorMessage.FILE_NOT_FOUND_EXCEPTION,
                Map.of("id", exception.getId())
        );
    }

    private ApiException mapCannotGetFileException(CannotGetFileException ex) {
        return ApiException.internalServerError(ex, FileErrorMessage.CANNOT_GET_FILE);
    }
}
