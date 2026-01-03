package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

public class CannotGetFileException extends DomainException {
    public CannotGetFileException() {
        super(new DomainError(FileErrorMessage.CANNOT_GET_FILE));
    }
}
