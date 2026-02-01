package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class CannotGetFileException extends DomainException {
    public CannotGetFileException() {
        super("Cannot get file");
    }
}
