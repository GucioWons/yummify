package com.guciowons.yummify.file.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.handler.ExceptionStatusResolver;
import com.guciowons.yummify.file.domain.exception.FileDomainException;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FileExceptionStatusResolver implements ExceptionStatusResolver {
    @Override
    public boolean supports(DomainException exception) {
        return exception instanceof FileDomainException;
    }

    @Override
    public HttpStatus resolve(DomainException exception) {
        return switch (exception) {
            case FileNotFoundException ignored -> HttpStatus.NOT_FOUND;
            case InvalidStorageKeyException ignored -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
