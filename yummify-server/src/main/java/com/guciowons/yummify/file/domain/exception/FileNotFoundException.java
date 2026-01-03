package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.Map;
import java.util.UUID;

public class FileNotFoundException extends DomainException {
    public FileNotFoundException(UUID id) {
        super(new DomainError(FileErrorMessage.FILE_NOT_FOUND_EXCEPTION, Map.of("id", id))
        );
    }
}
