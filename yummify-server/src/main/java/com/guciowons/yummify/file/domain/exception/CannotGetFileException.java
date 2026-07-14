package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.file.domain.exception.message.FileErrorMessage;

public class CannotGetFileException extends FileDomainException {
    public CannotGetFileException() {
        super(FileErrorMessage.CANNOT_GET_FILE);
    }
}
