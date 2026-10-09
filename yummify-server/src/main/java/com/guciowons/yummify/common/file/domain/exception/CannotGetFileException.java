package com.guciowons.yummify.common.file.domain.exception;

import com.guciowons.yummify.common.file.domain.exception.message.FileErrorMessage;

public class CannotGetFileException extends FileDomainException {
    public CannotGetFileException() {
        super(FileErrorMessage.CANNOT_GET_FILE);
    }
}
