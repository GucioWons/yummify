package com.guciowons.yummify.common.file.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.handler.ExceptionStatusResolver;
import com.guciowons.yummify.common.file.domain.exception.FileDomainException;
import com.guciowons.yummify.common.file.domain.exception.InvalidStorageKeyException;
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
            case InvalidStorageKeyException ignored -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
