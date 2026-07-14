package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.file.domain.exception.message.FileErrorMessage;

public class InvalidStorageKeyException extends FileDomainException {
    private InvalidStorageKeyException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public static InvalidStorageKeyException blank() {
        return new InvalidStorageKeyException(FileErrorMessage.STORAGE_KEY_IS_BLANK);
    }

    public static InvalidStorageKeyException startsWithSlash() {
        return new InvalidStorageKeyException(FileErrorMessage.STORAGE_KEY_STARTS_WITH_SLASH);
    }

    public static InvalidStorageKeyException containsDots() {
        return new InvalidStorageKeyException(FileErrorMessage.STORAGE_KEY_CONTAINS_DOTS);
    }
}
